package com.cleverpine.exceldatasync.dto.sap.app.optimization.header;

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
@ExcelSheet(name = "ZMDLP_RMA_170_729")
public class WarehouseAircraftExclusionsDto extends ExportExcelDto { //729

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "OPERATOR", index = 1)
    private String operator;
    @ExcelColumn(name = "AC_TYPE", index = 2)
    private String aircraftType; //acType
    @ExcelColumn(name = "EXCLUSION_NID", index = 3)
    private String exclusionNodeId; //exclusionNid
    @ExcelColumn(name = "Manuell", index = 4, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean manual = false;
}
