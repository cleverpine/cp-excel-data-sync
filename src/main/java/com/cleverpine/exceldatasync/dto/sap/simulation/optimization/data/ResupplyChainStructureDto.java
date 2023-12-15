package com.cleverpine.exceldatasync.dto.sap.simulation.optimization.data;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.dto.sap.contract.ProjectIdHandler;
import com.cleverpine.exceldatasync.dto.sap.contract.TopologyChangesHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_RMA_340_740")
public class ResupplyChainStructureDto extends ExportExcelDto implements ProjectIdHandler, TopologyChangesHandler { //740

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "MATERIAL_CYCLE", index = 1)
    private String materialCycle;
    @ExcelColumn(name = "PLC", index = 2)
    private String planningCycle;
    @ExcelColumn(name = "MRPGR", index = 3)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "NID_SOURCE", index = 4)
    private String nodeIdSource; //nidSource
    @ExcelColumn(name = "NID_TARGET", index = 5)
    private String nodeIdTarget; //nidTarget
    @ExcelColumn(name = "REGION_SOURCE", index = 6)
    private String regionSource;
    @ExcelColumn(name = "LEVEL_SOURCE", index = 7)
    private Integer levelSource;
    @ExcelColumn(name = "REGION_TARGET", index = 8)
    private String regionTarget;
    @ExcelColumn(name = "LEVEL_TARGET", index = 9)
    private Integer levelTarget;
    @ExcelColumn(name = "REPL_DELAY", index = 10)
    private Integer replDelay;

    private String locTypeSource;
    private String locTypeTarget;
    private String structureId;
    private Long projectId;
    private Long simulationId;
}
