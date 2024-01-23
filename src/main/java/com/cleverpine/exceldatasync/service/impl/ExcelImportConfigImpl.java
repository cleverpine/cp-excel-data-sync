package com.cleverpine.exceldatasync.service.impl;

import com.cleverpine.exceldatasync.service.api.ExcelImportConfig;
import com.cleverpine.exceldatasync.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExcelImportConfigImpl implements ExcelImportConfig {

    private int batchSize = Constants.BATCH_IMPORT_SIZE;

}
