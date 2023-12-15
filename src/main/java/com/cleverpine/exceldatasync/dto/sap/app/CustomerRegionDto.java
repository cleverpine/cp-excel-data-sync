package com.cleverpine.exceldatasync.dto.sap.app;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.mapper.impl.BooleanToUpperCaseMapperImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_RMA_130_REF")
public class CustomerRegionDto extends ExportExcelDto { //130

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "OPERATOR", index = 1)
    private String operatorCode; //operator
    @ExcelColumn(name = "REGION", index = 2)
    private String region;
    @ExcelColumn(name = "Manuell", index = 3, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean manual = false;
}
