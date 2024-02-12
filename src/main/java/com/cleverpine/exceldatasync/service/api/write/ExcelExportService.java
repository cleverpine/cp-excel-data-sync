package com.cleverpine.exceldatasync.service.api.write;

import com.cleverpine.exceldatasync.service.impl.write.ExcelExportConfig;

public interface ExcelExportService {

    byte[] export(ExcelExportConfig config);

}
