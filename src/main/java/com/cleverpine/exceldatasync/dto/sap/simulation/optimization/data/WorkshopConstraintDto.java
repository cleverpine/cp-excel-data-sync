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
@ExcelSheet(name = "ZMDLP_RMA_390_735")
public class WorkshopConstraintDto extends ExportExcelDto implements ProjectIdHandler { //735

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "MATERIAL_CYCLE", index = 1)
    private String materialCycle;
    @ExcelColumn(name = "PLC", index = 2)
    private String planningCycle;
    @ExcelColumn(name = "MRPGR", index = 3)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "NID", index = 4)
    private String nodeId; //nid
    @ExcelColumn(name = "TARGET_KEY", index = 5)
    private String constraintType; //targetKey
    @ExcelColumn(name = "TARGET_TYPE", index = 6)
    private String targetType;
    @ExcelColumn(name = "TARGET_VALUE", index = 7)
    private BigDecimal minNumOfDisposets; //targetValue (16,3)

    private Long projectId;
    private Long simulationId;
}
