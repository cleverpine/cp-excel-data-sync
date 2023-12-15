package com.cleverpine.exceldatasync.dto.sap.app;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.mapper.impl.BooleanToXMapperImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_RMA_150_REF")
public class OperatorOutOfSupplyRegionDto extends ExportExcelDto { //150

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "OPERATOR", index = 1)
    private String operatorCode; //operator
    @ExcelColumn(name = "ANFO_PRIO", index = 2)
    private String priority; //anfoPrio
    @ExcelColumn(name = "AC_TYPE", index = 3)
    private String aircraftType; //acType
    @ExcelColumn(name = "REG_SUPPLY", index = 4, mapper = BooleanToXMapperImpl.class)
    private Boolean regionRequest; //regSupply
}
