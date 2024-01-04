package com.cleverpine.exceldatasync.service.impl;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.exception.ExcelException;
import com.cleverpine.exceldatasync.service.api.ExcelConfig;
import com.cleverpine.exceldatasync.service.api.ExcelImportService;
import com.cleverpine.exceldatasync.util.Constants;
import com.cleverpine.exceldatasync.util.ExcelValueMapper;
import com.github.pjfanning.xlsx.StreamingReader;
import java.io.InputStream;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import static com.cleverpine.exceldatasync.util.Constants.EXCEL_COLUMN_ANNOTATION_IS_MISSING;
import static com.cleverpine.exceldatasync.util.Constants.EXCEL_SHEET_ANNOTATION_IS_MISSING;
import static com.cleverpine.exceldatasync.util.Constants.FAILED_TO_INITIALIZE_WORKBOOK_ERROR_MESSAGE;
import static com.cleverpine.exceldatasync.util.ExcelValueMapper.CELL_VALUE_FUNCTION_MAP;
import static com.cleverpine.exceldatasync.util.ExcelValueMapper.createInstance;
import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

public class ExcelImportServiceImpl implements ExcelImportService {

    private final VarHandleCache varHandleCache = new VarHandleCache();

    @Override
    public <Dto extends ExcelDto> void importFrom(InputStream inputStream, Class<Dto> dtoClass, ExcelConfig config, Consumer<List<Dto>> batchConsumer) {
        try (Workbook workbook = createWorkbook(inputStream)) {

            Iterator<Dto> iterator = getClassIterator(dtoClass, workbook);
            while (iterator.hasNext()) {
                int batchSize = config.getBatchSize();
                List<Dto> batch = createBatch(iterator, batchSize);
                batchConsumer.accept(batch);
            }

        } catch (Exception e) {
            throw new ExcelException(FAILED_TO_INITIALIZE_WORKBOOK_ERROR_MESSAGE, e);
        }
    }

    private Workbook createWorkbook(InputStream inputStream) {
        return StreamingReader.builder()
                .rowCacheSize(Constants.ROW_CACHE_SIZE)
                .bufferSize(Constants.BUFFER_SIZE)
                .open(inputStream);
    }

    private <Dto extends ExcelDto> Iterator<Dto> getClassIterator(Class<Dto> dtoClass, Workbook workbook) {
        Sheet sheet = getClassSheet(workbook, dtoClass);
        Field[] excelColumnFields = varHandleCache.getDeclaredFields(dtoClass);
        String sheetName = sheet.getSheetName();
        Iterator<Row> rowIterator = sheet.rowIterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return rowIterator.hasNext();
            }

            @Override
            public Dto next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    row = rowIterator.next();
                }
                return mapRow(dtoClass, row, sheetName, excelColumnFields);
            }
        };
    }

    private <Dto extends ExcelDto> Sheet getClassSheet(Workbook workbook, Class<Dto> dtoClass) {
        ExcelSheet sheetAnnotation = dtoClass.getAnnotation(ExcelSheet.class);
        if (sheetAnnotation == null) {
            throw new IllegalStateException(EXCEL_SHEET_ANNOTATION_IS_MISSING);
        }

        return workbook.getSheet(sheetAnnotation.name());
    }

    private <Dto extends ExcelDto> List<Dto> createBatch(Iterator<Dto> iterator, int batchSize) {
        List<Dto> batch = new ArrayList<>(batchSize);
        while (iterator.hasNext() && batch.size() < batchSize) {
            Dto element = iterator.next();
            batch.add(element);
        }

        return batch;
    }

    private <Dto extends ExcelDto> Dto mapRow(Class<Dto> dtoClass, Row row, String sheetName, Field[] excelColumnFields) {
        Dto dto = createInstance(dtoClass, sheetName);
        for (Field excelColumnField : excelColumnFields) {
            mapColumn(dtoClass, row, excelColumnField, dto);
        }

        return dto;
    }

    private <Dto extends ExcelDto> void mapColumn(Class<Dto> dtoClass, Row row, Field excelColumnField, Dto dto) {
        ExcelColumn columnAnnotation = excelColumnField.getAnnotation(ExcelColumn.class);
        if (columnAnnotation == null) {
            throw new IllegalStateException(EXCEL_COLUMN_ANNOTATION_IS_MISSING);
        }

        Cell cell = row.getCell(columnAnnotation.index(), CREATE_NULL_AS_BLANK);
        VarHandle varHandle = varHandleCache.getVarHandle(dtoClass, excelColumnField.getName());
        Object value = ExcelValueMapper.getValueFromCell(excelColumnField, CELL_VALUE_FUNCTION_MAP, cell, columnAnnotation.mapper());
        varHandle.set(dto, value);
    }
}
