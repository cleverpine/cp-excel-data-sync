package com.cleverpine.exceldatasync.service.impl.write.basic;

import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.service.impl.write.ExcelSheetExportConfig;
import com.cleverpine.exceldatasync.service.api.write.ExportPage;
import com.cleverpine.exceldatasync.service.api.write.ExportPageable;

import java.util.function.Function;

public class BasicExcelSheetExportConfig<Dto extends ExcelDto> extends ExcelSheetExportConfig<Dto> {

    public BasicExcelSheetExportConfig(Class<Dto> dtoClass, int pageSize,
                                       Function<ExportPageable, ExportPage<Dto>> pageDataFunction) {
        super(dtoClass, new BasicExportPageable(pageSize), pageDataFunction);
    }

    public BasicExcelSheetExportConfig(Class<Dto> dtoClass, ExportPageable initialPageable,
                                       Function<ExportPageable, ExportPage<Dto>> pageDataFunction) {
        super(dtoClass, initialPageable, pageDataFunction);
    }
}
