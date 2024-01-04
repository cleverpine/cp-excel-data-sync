package com.cleverpine.exceldatasync.annotations;

import com.cleverpine.exceldatasync.util.Constants;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelSheet {

    String name() default Constants.DEFAULT_SHEET_NAME;
}
