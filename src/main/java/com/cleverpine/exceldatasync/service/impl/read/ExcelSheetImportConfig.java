package com.cleverpine.exceldatasync.service.impl.read;

import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.service.api.read.ExcelImportConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public class ExcelSheetImportConfig<Dto extends ExcelDto> {

    private final Class<Dto> dtoClass;
    private final ExcelImportConfig config;
    private final Consumer<List<Dto>> batchConsumer;
}
