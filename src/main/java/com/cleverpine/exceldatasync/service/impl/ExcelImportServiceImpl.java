package com.cleverpine.exceldatasync.service.impl;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelMapper;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.exception.ExcelException;
import com.cleverpine.exceldatasync.service.api.ExcelImportConfig;
import com.cleverpine.exceldatasync.service.api.ExcelImportService;
import com.cleverpine.exceldatasync.util.Constants;
import com.github.pjfanning.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

import static com.cleverpine.exceldatasync.util.Constants.FAILED_TO_INITIALIZE_WORKBOOK_ERROR_MESSAGE;
import static com.cleverpine.exceldatasync.util.ExcelAnnotationHelper.*;
import static com.cleverpine.exceldatasync.util.ExcelColumnMapper.getColumnNumber;
import static com.cleverpine.exceldatasync.util.ExcelValueMapper.createInstance;
import static com.cleverpine.exceldatasync.util.ExcelValueMapper.mapCell;
import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

public class ExcelImportServiceImpl implements ExcelImportService {

    private final VarHandleCache varHandleCache = new VarHandleCache();

    @Override
    public <Dto extends ExcelDto> void importFrom(InputStream inputStream, Class<Dto> dtoClass, ExcelImportConfig config, Consumer<List<Dto>> batchConsumer) {
        try (Workbook workbook = createWorkbook(inputStream)) {

            Iterator<Dto> iterator = getClassIterator(dtoClass, workbook);
            while (iterator.hasNext()) {
                int batchSize = config.getBatchSize();
                List<Dto> batch = createBatch(iterator, batchSize);
                batchConsumer.accept(batch);
            }

        } catch (IOException e) {
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
        Field[] excelColumnFields = varHandleCache.getDeclaredFields(dtoClass);
        ExcelSheet sheetAnnotation = getSheetAnnotation(dtoClass);
        Sheet sheet = workbook.getSheet(sheetAnnotation.name());
        Iterator<Row> rowIterator = sheet.rowIterator();
        positionIterator(rowIterator, sheetAnnotation.startingRow());
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
                final Row row = rowIterator.next();
                return mapRow(row, dtoClass, excelColumnFields);
            }
        };
    }

    private void positionIterator(Iterator<Row> rowIterator, int startingRow) {
        if (!rowIterator.hasNext()) {
            return;
        }
        // The first call of returns first row that has data - could be before or after the desired starting row
        Row row = rowIterator.next();
        int startingRowIndex = startingRow - 1;
        if (row.getRowNum() > startingRowIndex) {
            // The first row that has data is after the desired starting row, so we can stop here
            throw new ExcelException("Defined starting row is before the first row with cell data.");
        }
        // Skip rows until we reach the starting row, the processing starts with the starting rows
        while (rowIterator.hasNext() && row.getRowNum() < startingRowIndex) {
            row = rowIterator.next();
        }
    }

    private <Dto extends ExcelDto> List<Dto> createBatch(Iterator<Dto> iterator, int batchSize) {
        List<Dto> batch = new ArrayList<>(batchSize);
        while (iterator.hasNext() && batch.size() < batchSize) {
            Dto element = iterator.next();
            batch.add(element);
        }

        return batch;
    }

    private <Dto extends ExcelDto> Dto mapRow(Row row, Class<Dto> dtoClass, Field[] excelColumnFields) {
        Dto dto = createInstance(dtoClass);
        for (Field excelColumnField : excelColumnFields) {
            mapColumn(excelColumnField, row, dto);
        }

        return dto;
    }

    private <Dto extends ExcelDto> void mapColumn(Field excelColumnField, Row row, Dto dto) {
        VarHandle varHandle = varHandleCache.getVarHandle(dto.getClass(), excelColumnField.getName());
        Cell cell = getCellFromRow(excelColumnField, row);
        Object value = getValueFromCell(excelColumnField, cell);
        varHandle.set(dto, value);
    }

    private Cell getCellFromRow(Field excelColumnField, Row row) {
        /**
         * TODO
         * You assume that all fields need a column annotation, but I am not sure if that is correct.
         * Maybe you want to allow for fields without a column annotation, and then just skip them here?
         */
        ExcelColumn columnAnnotation = getColumnAnnotation(excelColumnField)
                .orElseThrow(() -> new IllegalStateException(Constants.EXCEL_COLUMN_ANNOTATION_IS_MISSING));
        int columnNumber = getColumnNumber(columnAnnotation.letter());
        return row.getCell(columnNumber, CREATE_NULL_AS_BLANK);
    }

    private Object getValueFromCell(Field excelColumnField, Cell cell) {
        if (cell == null) {
            return null;
        }

        Optional<ExcelMapper> mapperAnnotation = getMapperAnnotation(excelColumnField);
        Class<?> fieldType = excelColumnField.getType();
        return mapCell(cell, fieldType, mapperAnnotation);
    }

}
