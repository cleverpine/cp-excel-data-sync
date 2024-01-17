package com.cleverpine.exceldatasync.service.api;

import com.cleverpine.exceldatasync.dto.ExcelDto;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ExcelExportService {

    byte[] export(ExcelExportConfig config);

}
