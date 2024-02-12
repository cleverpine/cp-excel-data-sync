package com.cleverpine.exceldatasync.service.api.read;

import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.service.impl.read.ExcelMultipleImportConfig;

import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

public interface ExcelImportService {

    <Dto extends ExcelDto> void importFrom(InputStream inputStream, Class<Dto> dtoClass, ExcelImportConfig config, Consumer<List<Dto>> batchConsumer);

    void importFrom(InputStream inputStream, ExcelMultipleImportConfig config);
}
