package com.cleverpine.exceldatasync.dto.sap.simulation.optimization.data;

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
@ExcelSheet(name = "ERG_RMA_860")
public class ErgRma860Dto extends ExportExcelDto {
    @ExcelColumn(name = "PLC", index = 0)
    private String planningCycle;
    @ExcelColumn(name = "MRPGR", index = 1)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "RST_MAINSTORE", index = 2)
    private BigDecimal rstMainstore;
    @ExcelColumn(name = "TOTAL_RFQ_FC", index = 3)
    private Integer totalRfqFc;
    @ExcelColumn(name = "TOTAL_QTY_CURR", index = 4)
    private Integer totalQtyCurr;
    @ExcelColumn(name = "TOTAL_COSTOPT", index = 5)
    private BigDecimal totalCostopt;
    @ExcelColumn(name = "REORDER_POINT", index = 6)
    private Integer reorderPoint;
    @ExcelColumn(name = "TOT_MWT", index = 7)
    private BigDecimal totMwt;
    @ExcelColumn(name = "TOT_RFQ", index = 8)
    private Integer totRfq;
    @ExcelColumn(name = "TOT_SERV_LEVEL", index = 9)
    private BigDecimal totServLevel;
    @ExcelColumn(name = "TOT_PROT_LEVEL", index = 10)
    private BigDecimal totProtLevel;
    @ExcelColumn(name = "TOT_ST_VALUE", index = 11)
    private BigDecimal totStValue;
    @ExcelColumn(name = "TOT_ST_OUT_COST", index = 12)
    private BigDecimal totStOutCost;
    @ExcelColumn(name = "TOT_LOAN_COST", index = 13)
    private BigDecimal totLoanCost;
    @ExcelColumn(name = "TOT_TOT_COST", index = 14)
    private BigDecimal totTotCost;
    @ExcelColumn(name = "TOT_CARR_COST", index = 15)
    private BigDecimal totCarrCost;
    @ExcelColumn(name = "TOT_NUM_ST_OUT", index = 16)
    private BigDecimal totNumStOut;
    @ExcelColumn(name = "TOT_NUM_DEM_SATI", index = 17)
    private BigDecimal totNumDemSati;
    @ExcelColumn(name = "TOT_NUM_LOANED", index = 18)
    private BigDecimal totNumLoaned;
    @ExcelColumn(name = "TOT_MAQ", index = 19)
    private BigDecimal totMaq;
    @ExcelColumn(name = "TOT_MGQ", index = 20)
    private BigDecimal totMgq;
    @ExcelColumn(name = "TOT_RFQ_COST_OPT", index = 21)
    private Integer totRfqCostOpt;
    @ExcelColumn(name = "NID_MAINSTORE", index = 22)
    private String nidMainstore;
}
