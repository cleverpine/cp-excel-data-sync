package com.cleverpine.exceldatasync.annotations;

import com.cleverpine.exceldatasync.mapper.ExcelCustomMapper;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelMapper {

    Class<? extends ExcelCustomMapper<?>> mapper();
}
