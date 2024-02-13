package com.cleverpine.exceldatasync.service.impl.write;

import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.service.api.write.ExportPage;
import com.cleverpine.exceldatasync.service.api.write.ExportPageable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public class ExcelSheetExportConfig<Dto extends ExcelDto> {

    private final Class<Dto> dtoClass;

    private final ExportPageable initialPageable;

    private final Function<ExportPageable, ExportPage<Dto>> pageDataFunction;
}
