package com.cleverpine.exceldatasync.dto.sap.simulation;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.dto.sap.contract.ProjectIdHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_RMA_303FF_472")
public class ExternalRepairTimeDto extends ExportExcelDto implements ProjectIdHandler { //472

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "MATERIAL_CYCLE", index = 1)
    private String materialCycle;
    @ExcelColumn(name = "MRPGR", index = 2)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "REGIO_GROUP", index = 3)
    private String regionGroup; //regioGroup
    @ExcelColumn(name = "REX", index = 4)
    private Integer repairTime; //rex
    @ExcelColumn(name = "SOURCE", index = 5)
    private String source;

    private Long projectId;
    private Long simulationId;
}
