package com.cleverpine.exceldatasync.service.impl;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.service.api.ExcelExportConfig;
import com.cleverpine.exceldatasync.service.api.ExcelExportService;
import com.cleverpine.exceldatasync.service.api.ExcelSheetExportConfig;
import com.cleverpine.exceldatasync.service.api.ExportPageable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelExportServiceImpl implements ExcelExportService {
    private static final String DEFAULT_SHEET_NAME = "Sheet";

    @Override
    public byte[] export(ExcelExportConfig config) {
        try (var workbook = new SXSSFWorkbook(); var outputStream = new ByteArrayOutputStream()) {
            processSheets(workbook, config);
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSheets(SXSSFWorkbook workbook, ExcelExportConfig config) {
        if (config == null || config.getSheets() == null || config.getSheets().isEmpty()) {
            return;
        }
        for (ExcelSheetExportConfig<? extends ExcelDto> sheetConfig : config.getSheets()) {
            processSingleSheet(workbook, sheetConfig);
        }
    }

    private <Dto extends ExcelDto> void processSingleSheet(
            SXSSFWorkbook workbook,
            ExcelSheetExportConfig<Dto> sheetConfig) {
        var dtoClass = sheetConfig.getDtoClass();
        var pageDataFunction = sheetConfig.getPageDataFunction();
        ExportPageable<Dto> pageable = null;
        do {
            pageable = pageDataFunction.apply(pageable);
            writeDataToWorkbook(workbook, pageable.getPageData(), dtoClass);
            pageable.next();
        } while (pageable.hasNext());
    }

    private  <Dto extends ExcelDto> void writeDataToWorkbook(
            SXSSFWorkbook workbook,
            List<Dto> dataList,
            Class<Dto> dtoClass) {
        var sheetAnnotation = dtoClass.getAnnotation(ExcelSheet.class);
        var sheetName = sheetAnnotation != null ? sheetAnnotation.name() : DEFAULT_SHEET_NAME;
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

        for (Dto dataObject : dataList) {
            if (dataObject == null) {
                continue;
            }
            var row = sheet.createRow(startRow++);
            writeDataRow(row, dataObject, fieldIndexMap);
        }
    }

    private <Dto extends ExcelDto> void writeDataRow(Row row, Dto dataObject, Map<Integer, Field> fieldIndexMap) {
        for (Map.Entry<Integer, Field> entry : fieldIndexMap.entrySet()) {
            var field = entry.getValue();
            field.setAccessible(true);

            try {
                if (field.isAnnotationPresent(ExcelColumn.class)) {
                    var annotation = field.getAnnotation(ExcelColumn.class);

                    // If mapper exists, use it
                    if (annotation.mapper() != E.class) {
                        var mappedValue = getMapperValue(dataObject, field);
                        if (mappedValue != null) {
                            assignCellValue(row.createCell(entry.getKey()), mappedValue);
                            continue;
                        }
                    }
                }

                var value = field.get(dataObject);
                if (value != null) {
                    assignCellValue(row.createCell(entry.getKey()), value);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);//TODO: custom exception
            }
        }
    }

    private void assignCellValue(Cell cell, Object value) {
        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Integer) {
            cell.setCellValue(((Integer) value).doubleValue());
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Long) {
            cell.setCellValue(((Long) value).doubleValue());
        } else if (value instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) value).doubleValue());
        } else if (value instanceof OffsetDateTime) {
            cell.setCellValue(((OffsetDateTime) value).toLocalDateTime());
        } else if (value instanceof LocalDateTime) {
            cell.setCellValue((LocalDateTime) value);
        } else {
            cell.setCellValue(value.toString());
        }
    }

    private <Dto extends ExcelDto> Map<Integer, Field> getFieldIndexMap(Class<Dto> clazz) {
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

    private <Dto extends ExcelDto> void writeHeaders(Sheet sheet, Class<Dto> clazz) {
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
}
