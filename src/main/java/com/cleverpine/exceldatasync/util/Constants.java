package com.cleverpine.exceldatasync.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Constants {

    //Error messages
    public static final String FAILED_TO_INSTANTIATE = "Failed to instantiate %s";
    public static final String FAILED_WRITING_STREAM_ERROR_MESSAGE = "Error writing to the output stream.";

    public static final String EXCEL_SHEET_ANNOTATION_IS_MISSING = "ExcelSheet annotation is missing";
    public static final String EXCEL_COLUMN_ANNOTATION_IS_MISSING = "ExcelColumn annotation is missing";
    public static final String FAILED_TO_INITIALIZE_WORKBOOK_ERROR_MESSAGE = "Error while initializing workbook";

    //Default values
    public static final int DEFAULT_SHEET_COLUMN_INDEX = -1;
    public static final String DEFAULT_SHEET_NAME = "Sheet";
    public static final int DEFAULT_PAGE_REQUEST_SIZE = 1000;

    public static final int GENERATE_EXCEL_CHUNK_SIZE = 5000;

    public static final String FAILED_TO_CLOSE_WORKBOOK_ERROR_MESSAGE = "Error while closing workbook";
    public static final String MAPPER = "mapper";
    public static final int BATCH_IMPORT_SIZE = 1000;
    public static final int DEFAULT_OFFSET = 0;

    public static final int ROW_CACHE_SIZE = 1000;
    public static final int BUFFER_SIZE = 1024 * 5;

}
