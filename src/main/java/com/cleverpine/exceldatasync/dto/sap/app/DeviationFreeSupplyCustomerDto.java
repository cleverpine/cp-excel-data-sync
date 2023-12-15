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
@ExcelSheet(name = "ZMDLP_RMA_151_REF")
public class DeviationFreeSupplyCustomerDto extends ExportExcelDto { //151

    @ExcelColumn(name = "OPERATOR", index = 0)
    private String operatorCode; //operator
    @ExcelColumn(name = "AC_TYPE", index = 1)
    private String aircraftType; //acType
    @ExcelColumn(name = "Manuell", index = 2, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean manual = false;
}
