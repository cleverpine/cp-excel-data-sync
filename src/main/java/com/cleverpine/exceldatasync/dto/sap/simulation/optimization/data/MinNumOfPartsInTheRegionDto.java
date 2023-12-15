package com.cleverpine.exceldatasync.dto.sap.simulation.optimization.data;

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
@ExcelSheet(name = "ZMDLP_RMA_311_734")
public class MinNumOfPartsInTheRegionDto extends ExportExcelDto implements ProjectIdHandler { //734

    @ExcelColumn(name = "MATERIAL_CYCLE", index = 0)
    private String materialCycle;
    @ExcelColumn(name = "PLC", index = 1)
    private String planningCycle;
    @ExcelColumn(name = "MRPGR", index = 2)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "STATUS_EM", index = 3)
    private Integer quantityEm;
    @ExcelColumn(name = "STATUS_AM", index = 4)
    private Integer quantityAm;
    @ExcelColumn(name = "STATUS_AP", index = 5)
    private Integer quantityAp;

    private Long projectId;
    private Long simulationId;
}
