package com.cleverpine.exceldatasync.dto.sap.app.optimization.header;

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
@ExcelSheet(name = "ZMDLP_RMA_105_722")
public class TransportCostDto extends ExportExcelDto { //722

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "REGION_SOURCE", index = 1)
    private String regionSource;
    @ExcelColumn(name = "REGION_TARGET", index = 2)
    private String regionTarget;
    @ExcelColumn(name = "RTY", index = 3)
    private String transportGroup; //routeType
    @ExcelColumn(name = "COST_TRANSPORT", index = 4)
    private BigDecimal transportCost; //costTransport (16,2)
}
