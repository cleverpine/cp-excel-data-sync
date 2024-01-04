package com.cleverpine.exceldatasync.mapper.impl;

import com.cleverpine.exceldatasync.mapper.ExcelExportCustomMapper;
import java.util.Optional;

public class BooleanToUpperCaseMapperImpl implements ExcelExportCustomMapper<Boolean> {

    @Override
    public String toString(Boolean value) {
        return Boolean.toString(value != null && value).toUpperCase();
    }

    @Override
    public Boolean fromString(String value) {
        return Optional.ofNullable(value)
                .map(String::toLowerCase)
                .map(Boolean::parseBoolean)
                .orElse(null);
    }
}
