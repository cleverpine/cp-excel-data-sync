package com.cleverpine.exceldatasync.util;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelMapper;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExcelDto;
import java.lang.reflect.Field;
import java.util.Optional;
import lombok.experimental.UtilityClass;


import static com.cleverpine.exceldatasync.util.Constants.EXCEL_COLUMN_ANNOTATION_IS_MISSING;
import static com.cleverpine.exceldatasync.util.Constants.EXCEL_SHEET_ANNOTATION_IS_MISSING;

@UtilityClass
public final class ExcelAnnotationHelper {

    public static <Dto extends ExcelDto> ExcelSheet getSheetAnnotation(Class<Dto> excelDtoClass) {
        ExcelSheet sheetAnnotation = excelDtoClass.getAnnotation(ExcelSheet.class);
        if (sheetAnnotation == null) {
            throw new IllegalStateException(EXCEL_SHEET_ANNOTATION_IS_MISSING);
        }
        return sheetAnnotation;
    }

    public static Optional<ExcelColumn> getColumnAnnotation(Field excelColumnField) {
        return Optional.ofNullable(excelColumnField.getAnnotation(ExcelColumn.class));
    }

    public static Optional<ExcelMapper> getMapperAnnotation(Field excelColumnField) {
        return Optional.ofNullable(excelColumnField.getAnnotation(ExcelMapper.class));
    }

}
