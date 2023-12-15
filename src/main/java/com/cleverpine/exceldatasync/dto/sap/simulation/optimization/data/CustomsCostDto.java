package com.cleverpine.exceldatasync.dto.sap.simulation.optimization.data;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.dto.sap.contract.ProjectIdHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_RMA_350_736")
public class CustomsCostDto extends ExportExcelDto implements ProjectIdHandler { //736

    @ExcelColumn(name = "MATERIAL_CYCLE", index = 0)
    private String materialCycle;
    @ExcelColumn(name = "MANDT", index = 1)
    private String mandt = "002";
    @ExcelColumn(name = "MRPGR", index = 2)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "REGION_SOURCE", index = 3)
    private String regionSource;
    @ExcelColumn(name = "REGION_TARGET", index = 4)
    private String regionTarget;
    @ExcelColumn(name = "SERVICEABLE_COST", index = 5)
    private BigDecimal serviceableCost; // (13,2)
    @ExcelColumn(name = "UNSERVICEABLE_COST", index = 6)
    private BigDecimal unServiceableCost; // (13,2)

    private Long projectId;
    private Long simulationId;
}
