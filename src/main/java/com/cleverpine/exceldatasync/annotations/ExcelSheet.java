package com.cleverpine.exceldatasync.annotations;

import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.util.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.cleverpine.exceldatasync.util.Constants.DEFAULT_STARTING_ROW;

/**
 * Annotation used to map a class implementing {@link ExcelDto} to a specific sheet in an Excel file
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelSheet {

    /**
     * The name of the sheet in the Excel file
     *
     * @return the name of the sheet in the Excel file
     */
    String name();

    /**
     * The row number in the sheet from where to start reading the data.
     * <p>
     * Typically, this is the row number of the table headers, but if there are no headers, this should be the row number
     * of the row that contains the data.
     *
     * @return the row number. Default is {@value Constants#DEFAULT_STARTING_ROW}
     */
    int startingRow() default DEFAULT_STARTING_ROW;
}
