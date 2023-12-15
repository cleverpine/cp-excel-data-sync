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
@ExcelSheet(name = "ZMDLP_RMA_180_721")
public class CostFunctionDto extends ExportExcelDto { //721

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "COSTFUNCTION_ID", index = 1)
    private String costfunctionId;
    @ExcelColumn(name = "COST_STOCK_OUT", index = 2)
    private BigDecimal costStockOut; // (13,2)
    @ExcelColumn(name = "COST_LOAN_FIX", index = 3)
    private BigDecimal costLoanFix; // (13,2)
    @ExcelColumn(name = "COST_LOAN_ONCE", index = 4)
    private BigDecimal costLoanOnce; // (7,6)
    @ExcelColumn(name = "COST_LOAN_DAILY", index = 5)
    private BigDecimal costLoanDaily; // (7,6)
    @ExcelColumn(name = "ANNUAL_DEPR_RATE", index = 6)
    private BigDecimal annualDeprRate; // (7,6)
    @ExcelColumn(name = "CURVATURE", index = 7)
    private BigDecimal curvature; // (13,2)
    @ExcelColumn(name = "PROB_MIN", index = 8)
    private BigDecimal probMin; // (7,6)
    @ExcelColumn(name = "PROB_MAX", index = 9)
    private BigDecimal probMax; // (7,6)
}
