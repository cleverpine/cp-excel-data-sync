package com.cleverpine.exceldatasync.service.api;

import com.cleverpine.exceldatasync.dto.ExcelDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public class ExcelSheetExportConfig<Dto extends ExcelDto> {

    private final Class<Dto> dtoClass;

    private final Function<ExportPageable<Dto>, ExportPageable<Dto>> pageDataFunction;

}
