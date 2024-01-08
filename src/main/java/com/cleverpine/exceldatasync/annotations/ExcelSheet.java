package com.cleverpine.exceldatasync.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import static com.cleverpine.exceldatasync.util.Constants.DEFAULT_STARTING_ROW;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelSheet {

    String name();

    int startingRow() default DEFAULT_STARTING_ROW;

}
