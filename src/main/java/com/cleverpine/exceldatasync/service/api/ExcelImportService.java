package com.cleverpine.exceldatasync.service.api;

import com.cleverpine.exceldatasync.dto.ExcelDto;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

public interface ExcelImportService {

    <Dto extends ExcelDto> void importFrom(InputStream inputStream, Class<Dto> dtoClass, ExcelImportConfig config, Consumer<List<Dto>> batchConsumer);

}
