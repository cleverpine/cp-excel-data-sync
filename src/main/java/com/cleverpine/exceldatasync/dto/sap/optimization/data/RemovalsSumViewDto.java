package com.cleverpine.exceldatasync.dto.sap.optimization.data;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_RMA_900_Summen")
public class RemovalsSumViewDto extends ExportExcelDto {

    @ExcelColumn(name = "PLC", index = 0)
    private String deviationCode; //plc
    @ExcelColumn(name = "MRPGR", index = 1)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "SUM_REMOVALS", index = 2)
    private BigDecimal sumRemovals;
    @ExcelColumn(name = "SUM_REMOVALS_ohne_POT", index = 3)
    private BigDecimal sumRemovalsWithoutPot;
}
