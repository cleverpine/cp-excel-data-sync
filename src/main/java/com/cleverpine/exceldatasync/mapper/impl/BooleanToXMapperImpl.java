package com.cleverpine.exceldatasync.mapper.impl;

import com.cleverpine.exceldatasync.mapper.ExcelCustomMapper;

public class BooleanToXMapperImpl implements ExcelCustomMapper<Boolean> {

    public static final String SELECTED = "X";

    @Override
    public String toString(Boolean value) {
        return (value != null && value) ? SELECTED : "";
    }

    @Override
    public Boolean fromString(String value) {
        if (value == null) {
            return null;
        }
        if (value.isEmpty()) {
            return false;
        }
        if (value.equalsIgnoreCase(SELECTED)) {
            return true;
        }
        throw new IllegalArgumentException("Value must be " + SELECTED + " or empty");
    }
}
