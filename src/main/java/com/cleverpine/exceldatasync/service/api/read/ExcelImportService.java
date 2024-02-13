package com.cleverpine.exceldatasync.service.api.read;

import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.service.impl.read.ExcelMultipleImportConfig;
import com.cleverpine.exceldatasync.service.impl.read.ExcelSheetImportConfig;

import java.io.InputStream;

public interface ExcelImportService {

    <Dto extends ExcelDto> void importFrom(InputStream inputStream, ExcelSheetImportConfig<Dto> importConfig);

    void importFrom(InputStream inputStream, ExcelMultipleImportConfig config);
}
