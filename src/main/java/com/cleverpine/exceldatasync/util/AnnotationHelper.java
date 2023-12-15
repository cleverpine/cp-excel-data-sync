package com.cleverpine.exceldatasync.util;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;

import java.util.Optional;

public final class AnnotationHelper {

    private AnnotationHelper() {
        // Should not be instantiated
    }

    public static ExcelSheet getExcelSheetAnnotationOrThrow(Class<?> excelSheetClass) {
        final var excelSheetAnnotation = excelSheetClass.getAnnotation(ExcelSheet.class);
        if (excelSheetAnnotation == null) {
            throw new IllegalStateException(Constants.EXCEL_SHEET_ANNOTATION_IS_MISSING);
        }
        return excelSheetAnnotation;
    }

    public static ExcelColumn getExcelColumnAnnotationOrThrow(Class<?> excelSheetClass) {
        final var excelSheetAnnotation = excelSheetClass.getAnnotation(ExcelColumn.class);
        if (excelSheetAnnotation == null) {
            throw new IllegalStateException(Constants.EXCEL_COLUMN_ANNOTATION_IS_MISSING);
        }
        return excelSheetAnnotation;
    }

    public static Optional<ExcelSheet> getExcelSheetAnnotation(Class<?> clazz) {
        return Optional.ofNullable(clazz.getAnnotation(ExcelSheet.class));
    }

    public static Optional<ExcelColumn> getExcelColumnAnnotation(Class<?> clazz) {
        return Optional.ofNullable(clazz.getAnnotation(ExcelColumn.class));
    }
}
