package com.cleverpine.exceldatasync.mapper;

// Maybe rename to ExcelCustomMapper -> as it will be used for import and export
public interface ExcelExportCustomMapper<T> {

    String toString(T value);

    T fromString(String value);
}
