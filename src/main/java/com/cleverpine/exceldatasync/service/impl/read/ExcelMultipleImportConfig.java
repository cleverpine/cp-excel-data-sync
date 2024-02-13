package com.cleverpine.exceldatasync.service.impl.read;

import com.cleverpine.exceldatasync.dto.ExcelDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ExcelMultipleImportConfig {

    private final List<ExcelSheetImportConfig<? extends ExcelDto>> sheets;
}
