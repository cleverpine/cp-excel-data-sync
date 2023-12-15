package com.cleverpine.exceldatasync.util;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExcelFileDto;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.exception.ExcelException;
import com.cleverpine.exceldatasync.mapper.ExcelExportCustomMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.cleverpine.exceldatasync.util.Constants.FAILED_WRITING_STREAM_ERROR_MESSAGE;
import static com.cleverpine.exceldatasync.util.Constants.FAILED_TO_INITIALIZE_WORKBOOK_ERROR_MESSAGE;
import static com.cleverpine.exceldatasync.util.ExcelHelper.getMapperValue;

//
//@Component
public class ExportExcelHelper {

    public <Entity, DTO extends ExportExcelDto> void fetchAllAndMapAndWrite(
        Pageable pageable,
        Function<Pageable, Page<Entity>> fetchFunction,
        Function<Entity, DTO> mapFunction,
        SXSSFWorkbook workbook,
        Class<DTO> dtoClass
    ) {

//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, customerReturnTimeService::getAll, workbook, CustomerReturnTimeDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, disposetService::findDistinctDisposetsBySimulationId, workbook, DisposetDto.class);
        Page<Entity> page;
        List<DTO> currentPageDtos;

        do {
            page = fetchFunction.apply(pageable);
            currentPageDtos = page.stream()
                .map(mapFunction)
                .collect(Collectors.toList());

            writeDataToWorkbook(workbook, currentPageDtos, dtoClass);

            pageable = pageable.next();
        } while (page.hasNext());
    }

    public <DTO extends ExportExcelDto> void fetchAllAndMapAndWrite(
        Pageable pageable,
        Function<Pageable, Page<DTO>> fetchFunction,
        SXSSFWorkbook workbook,
        Class<DTO> dtoClass
    ) {
        fetchAllAndMapAndWrite(pageable, fetchFunction, Function.identity(), workbook, dtoClass);
    }

    public <Entity, DTO extends ExportExcelDto> void fetchAllAndMapByIdAndWrite(
        Long id, Pageable pageable,
        BiFunction<Long, Pageable, Page<Entity>> fetchFunction,
        Function<Entity, DTO> mapFunction,
        SXSSFWorkbook workbook,
        Class<DTO> dtoClass
    ) {

        Page<Entity> page;
        List<DTO> currentPageDtos;

        do {
            page = fetchFunction.apply(id, pageable);
            currentPageDtos = page.stream()
                .map(mapFunction)
                .collect(Collectors.toList());

            writeDataToWorkbook(workbook, currentPageDtos, dtoClass);

            pageable = pageable.next();
        } while (page.hasNext());
    }

    public <DTO extends ExportExcelDto> void fetchAllAndMapByIdAndWrite(
        Long id, Pageable pageable,
        BiFunction<Long, Pageable, Page<DTO>> fetchFunction,
        SXSSFWorkbook workbook,
        Class<DTO> dtoClass
    ) {
        fetchAllAndMapByIdAndWrite(id, pageable, fetchFunction, Function.identity(), workbook, dtoClass);
    }

    public <S extends ExportExcelDto> List<List<S>> splitIntoChunks(List<S> list) {
        var chunkSize = Constants.GENERATE_EXCEL_CHUNK_SIZE;
        List<List<S>> chunks = new ArrayList<>();

        for (int i = 0; i < list.size(); i += chunkSize) {
            var chunk = new ArrayList<>(list.subList(i, Math.min(i + chunkSize, list.size())));
            chunks.add(chunk);
        }

        return chunks;
    }

    public <S extends ExportExcelDto> void writeDataToWorkbook(SXSSFWorkbook workbook, List<S> dataList, Class<S> dtoClass) {
        var sheetAnnotation = dtoClass.getAnnotation(ExcelSheet.class);
        var sheetName = sheetAnnotation != null ? sheetAnnotation.name() : Constants.DEFAULT_SHEET_NAME;
        var sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
            writeHeaders(sheet, dtoClass);
        }

        var fieldIndexMap = getFieldIndexMap(dtoClass);

        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        var startRow = sheet.getLastRowNum() + 1;

        for (S dataObject : dataList) {
            var row = sheet.createRow(startRow++);
            writeDataRow(row, dataObject, fieldIndexMap);
        }
    }

    public ExcelFileDto getExcelFileDto(SXSSFWorkbook workbook, String filename) {
        try (
            SXSSFWorkbook wb = workbook;
            ByteArrayOutputStream bos = new ByteArrayOutputStream()
        ) {
            wb.write(bos);
            byte[] bytes = bos.toByteArray();

            return new ExcelFileDto(bytes, filename);
        } catch (IOException e) {
            throw new ExcelException(FAILED_WRITING_STREAM_ERROR_MESSAGE, e);
        }
    }

    private <S extends ExportExcelDto> Map<Integer, Field> getFieldIndexMap(Class<S> clazz) {
        var fields = clazz.getDeclaredFields();
        Map<Integer, Field> fieldIndexMap = new HashMap<>();

        for (Field field : fields) {
            var column = field.getAnnotation(ExcelColumn.class);
            if (column != null) {
                fieldIndexMap.put(column.index(), field);
            }
        }
        return fieldIndexMap;
    }

    private <S extends ExportExcelDto> void writeHeaders(Sheet sheet, Class<S> clazz) {
        var headerRow = sheet.createRow(0);

        // Set font to bold
        var font = sheet.getWorkbook().createFont();
        font.setBold(true);

        var fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            var column = field.getAnnotation(ExcelColumn.class);
            if (column != null) {
                // Set column name
                var headerCell = headerRow.createCell(column.index());
                headerCell.setCellValue(column.name());

                var style = sheet.getWorkbook().createCellStyle();
                style.setFont(font);

                setBackgroundColor(column, style);

                headerCell.setCellStyle(style);
            }
        }
    }

    private static void setBackgroundColor(ExcelColumn column, CellStyle style) {
        if (!column.backgroundColor().isEmpty()) {
            // Set cell background color
            IndexedColors indexedColor = IndexedColors.valueOf(column.backgroundColor());
            style.setFillForegroundColor(indexedColor.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Set cell borders
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
        }
    }

    private void writeDataRow(Row row, ExportExcelDto dataObject, Map<Integer, Field> fieldIndexMap) {
        for (Map.Entry<Integer, Field> entry : fieldIndexMap.entrySet()) {
            var field = entry.getValue();
            field.setAccessible(true);

            try {
                if (field.isAnnotationPresent(ExcelColumn.class)) {
                    var annotation = field.getAnnotation(ExcelColumn.class);

                    // If mapper exists, use it
                    if (annotation.mapper() != ExcelExportCustomMapper.class) {
                        var mappedValue = getMapperValue(dataObject, field);
                        if (mappedValue != null) {
                            row.createCell(entry.getKey()).setCellValue(mappedValue);
                            continue;
                        }
                    }
                }

                var value = field.get(dataObject);
                if (value != null) {
                    row.createCell(entry.getKey()).setCellValue(value.toString());
                }
            } catch (Exception e) {
                throw new ExcelException(FAILED_TO_INITIALIZE_WORKBOOK_ERROR_MESSAGE, e);
            }
        }
    }
}
