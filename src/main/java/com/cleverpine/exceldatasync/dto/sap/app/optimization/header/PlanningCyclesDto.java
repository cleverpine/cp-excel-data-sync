package com.cleverpine.exceldatasync.dto.sap.app.optimization.header;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_RMA_103_724")
public class PlanningCyclesDto extends ExportExcelDto { //724

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "PLC", index = 1)
    private String planningCycle;
    @ExcelColumn(name = "PLC_NAME", index = 2)
    private String plcName;
    @ExcelColumn(name = "DEVCODE", index = 3)
    private String deviationCode; //devCode
    @ExcelColumn(name = "ID", index = 4)
    private String planningCycleId;
}
