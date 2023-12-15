package com.cleverpine.exceldatasync.util;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import static com.cleverpine.exceldatasync.util.ExcelVarHandleInitializer.REFERENCE_FILE_SHEET_CLASSES;
import static com.cleverpine.exceldatasync.util.ExcelHelper.CELL_VALUE_FUNCTION_MAP;
import static com.cleverpine.exceldatasync.util.ReflectionUtils.createInstance;
import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

@RequiredArgsConstructor
public class ImportExcelHelper {

    public void importReferenceFileDto(Workbook workbook) {
        REFERENCE_FILE_SHEET_CLASSES.forEach(excelSheetClass -> mapSheet(excelSheetClass, workbook));
    }

    private void mapSheet(Class<? extends ExportExcelDto> excelSheetClass, Workbook workbook) {
        var excelSheetAnnotation = AnnotationHelper.getExcelSheetAnnotationOrThrow(excelSheetClass);
        var sheetName = excelSheetAnnotation.name();

        var sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            return;
        }

        var excelColumnFields = ExcelVarHandleInitializer.getDeclaredFields(excelSheetClass);
        var dataObjectsList = new ArrayList<>(sheet.getLastRowNum());
        for (var row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            var dataObject = mapRow(excelSheetClass, row, sheetName, excelColumnFields);
            dataObjectsList.add(dataObject);
            if (dataObjectsList.size() >= Constants.BATCH_IMPORT_SIZE) {
                //TODO: Add to list or collection or iterable or stream the return value of this method
                dataObjectsList.clear();
            }
        }
        if (!dataObjectsList.isEmpty()) {
            //TODO: Add to list or collection or iterable or stream the return value of this method
        }
    }

    private ExportExcelDto mapRow(Class<? extends ExportExcelDto> excelSheetClass, Row row, String sheetName,
            Field[] excelColumnFields) {
        var dataObject = createInstance(excelSheetClass, sheetName);
        Arrays.stream(excelColumnFields).forEach(excelColumnField -> mapColumn(excelSheetClass, row, excelColumnField, dataObject));
        return dataObject;
    }

    private void mapColumn(Class<? extends ExportExcelDto> excelSheetClass, Row row, Field excelColumnField, ExportExcelDto dataObject) {
        var excelColumnAnnotation = excelColumnField.getAnnotation(ExcelColumn.class);
        if (excelColumnAnnotation == null) {
            return;
        }
        var columnIndex = excelColumnAnnotation.index();
        var mapperClass = excelColumnAnnotation.mapper();

        var cell = row.getCell(columnIndex, CREATE_NULL_AS_BLANK);

        var varHandle = ExcelVarHandleInitializer.getVarHandle(excelSheetClass, excelColumnField.getName());
        var value = ExcelHelper.getValueFromCell(excelColumnField, CELL_VALUE_FUNCTION_MAP, cell, mapperClass);

        varHandle.set(dataObject, value);
    }
}
