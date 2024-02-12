package com.cleverpine.exceldatasync.service.impl.write;

import com.cleverpine.exceldatasync.dto.ExcelDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ExcelExportConfig {

    private final List<ExcelSheetExportConfig<? extends ExcelDto>> sheets;

}
