package com.cleverpine.exceldatasync.dto.sap.simulation;

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
@ExcelSheet(name = "ZMDLP_302_REF")
public class DisposetRemovalDto extends ExportExcelDto implements ProjectIdHandler { //302

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "MATERIAL_CYCLE", index = 1)
    private String materialCycle;
    @ExcelColumn(name = "MRPGR", index = 2)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "MEM_MTBR", index = 3)
    private Integer flightHoursBeforeReplacement; //memMtbr
    @ExcelColumn(name = "WEIGHT_MTBR", index = 4)
    private BigDecimal weightMtbr; // (16,6)
    @ExcelColumn(name = "MEM_MCBR", index = 5)
    private Integer flightCyclesBeforeReplacement; //memMcbr
    @ExcelColumn(name = "WEIGHT_MCBR", index = 6)
    private BigDecimal weightMcbr; // (16,6)
    @ExcelColumn(name = "MEM_MDBR", index = 7)
    private Integer daysBeforeReplacement; //memMdbr
    @ExcelColumn(name = "WEIGHT_MDBR", index = 8)
    private BigDecimal weightMdbr; // (16,6)

    private String muster;
    private String engmuster;
    private String operatorCode; //operator3lc
    private Integer mtbr;
    private Integer mcbr;
    private String sourceMxbr;
    private Long projectId;
    private Long simulationId;
}
