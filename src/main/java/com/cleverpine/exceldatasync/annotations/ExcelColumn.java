package com.cleverpine.exceldatasync.annotations;

import com.cleverpine.exceldatasync.mapper.ExcelExportCustomMapper;
import com.cleverpine.exceldatasync.util.Constants;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    String name();

    //Rename to columnOffset
    //add new rowOffset
    int index() default Constants.DEFAULT_SHEET_COLUMN_INDEX;

    Class<? extends ExcelExportCustomMapper> mapper() default ExcelExportCustomMapper.class;

    String backgroundColor() default "";
}
