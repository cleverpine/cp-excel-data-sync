package com.cleverpine.exceldatasync.mapper;

public interface ExcelCustomMapper<T> {

    String toString(T value);

    T fromString(String value);

}
