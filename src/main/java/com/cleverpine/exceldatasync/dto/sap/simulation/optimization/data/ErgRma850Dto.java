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
@ExcelSheet(name = "ERG_RMA_850")
public class ErgRma850Dto extends ExportExcelDto {
    @ExcelColumn(name = "PLC", index = 0)
    private String planningCycle;
    @ExcelColumn(name = "BOMNO", index = 1)
    private String contractNumber; //bomNo
    @ExcelColumn(name = "CATEGORY", index = 2)
    private String removalsType; //category
    @ExcelColumn(name = "MRPGR", index = 3)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "MRPGR_DIS", index = 4)
    private String disDisposetNumber; //disMrpgr
    @ExcelColumn(name = "OPERATOR", index = 5)
    private String operatorCode; //operator
    @ExcelColumn(name = "TRANS_LOC", index = 6)
    private String handoverLocation; //transLoc
    @ExcelColumn(name = "ANFO_PRIO", index = 7)
    private String priority; //anfoPrio
    @ExcelColumn(name = "REGION", index = 8)
    private String region;
    @ExcelColumn(name = "REG_SUPPLY", index = 9)
    private Boolean regionRequest; //regSupply
    @ExcelColumn(name = "RTY", index = 10)
    private String transportGroup; //rty
    @ExcelColumn(name = "NID", index = 11)
    private String nodeId; //nid
    @ExcelColumn(name = "LIST_ID", index = 12)
    private String listId;
    @ExcelColumn(name = "AC_TYPE", index = 13)
    private String aircraftType; //acType
    @ExcelColumn(name = "SUM_REMOVALS", index = 14)
    private BigDecimal sumRemovals; // (20,9)
    @ExcelColumn(name = "SUM_REMOVALS_FC", index = 15)
    private BigDecimal sumRemovalsFc; // (20,9)
    @ExcelColumn(name = "VTIME", index = 16)
    private Integer vTime;
    @ExcelColumn(name = "LOGISTIK", index = 17)
    private Integer logisticTime;
    @ExcelColumn(name = "TOLERANCE", index = 18)
    private Integer tolerance;
}
