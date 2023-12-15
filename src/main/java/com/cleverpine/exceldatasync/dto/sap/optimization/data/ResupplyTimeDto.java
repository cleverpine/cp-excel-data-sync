package com.cleverpine.exceldatasync.dto.sap.optimization.data;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.dto.sap.contract.ProjectIdHandler;
import com.cleverpine.exceldatasync.mapper.impl.BooleanToUpperCaseMapperImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_RMA_305_731")
public class ResupplyTimeDto extends ExportExcelDto implements ProjectIdHandler { //731

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "MATERIAL_CYCLE", index = 1)
    private String materialCycle;
    @ExcelColumn(name = "MRPGR", index = 2)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "REGIO_GROUP", index = 3)
    private String regionGroup; //regioGroup
    @ExcelColumn(name = "RST", index = 4)
    private BigDecimal resupplyTime; //rst
    @ExcelColumn(name = "Manuell", index = 5, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean manual = false;

    private Long projectId;
    private Long simulationId;
}
