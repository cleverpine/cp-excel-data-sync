package com.cleverpine.exceldatasync.mapper.impl;

import com.cleverpine.exceldatasync.mapper.ExcelCustomMapper;

public class BooleanToXMapperImpl implements ExcelCustomMapper<Boolean> {

    @Override
    public String toString(Boolean value) {
        return (value != null && value) ? "X" : "";
    }

    @Override
    public Boolean fromString(String value) {
        if (value == null) {
            return null;
        }
        if (value.isEmpty()) {
            return false;
        }
        if (value.equalsIgnoreCase("X")) {
            return true;
        }
        throw new IllegalArgumentException("Value must be X or empty");
    }
}
