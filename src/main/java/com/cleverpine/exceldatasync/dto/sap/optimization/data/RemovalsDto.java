package com.cleverpine.exceldatasync.dto.sap.optimization.data;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.dto.sap.contract.ProjectIdHandler;
import com.cleverpine.exceldatasync.mapper.impl.BooleanToUpperCaseMapperImpl;
import com.cleverpine.exceldatasync.mapper.impl.BooleanToXMapperImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_RMA_900_750")
public class RemovalsDto extends ExportExcelDto implements ProjectIdHandler { //750

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "MATERIAL_CYCLE", index = 1)
    private String materialCycle;
    @ExcelColumn(name = "PLC", index = 2)
    private String deviationCode; //plc
    @ExcelColumn(name = "BOMNO", index = 3)
    private String contractNumber; //bomNo
    @ExcelColumn(name = "CATEGORY", index = 4)
    private String removalsType; //category
    @ExcelColumn(name = "MRPGR", index = 5)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "MRPGR_DIS", index = 6)
    private String disDisposetNumber; //disMrpgr
    @ExcelColumn(name = "OPERATOR", index = 7)
    private String operator;
    @ExcelColumn(name = "TRANS_LOC", index = 8)
    private String handoverLocation; //transLoc
    @ExcelColumn(name = "ANFO_PRIO", index = 9)
    private String priority; //prio
    @ExcelColumn(name = "REGION", index = 10)
    private String region;
    @ExcelColumn(name = "REG_SUPPLY", index = 11, mapper = BooleanToXMapperImpl.class)
    private Boolean regionalSupply;
    @ExcelColumn(name = "RTY", index = 12)
    private String transportGroup; //rty
    @ExcelColumn(name = "NID", index = 13)
    private String nodeId; //nid
    @ExcelColumn(name = "LIST_ID", index = 14)
    private String listId;
    @ExcelColumn(name = "SUM_REMOVALS", index = 15)
    private BigDecimal sumRemovals; // (20,9)
    @ExcelColumn(name = "SUM_REMOVALS_FC", index = 16)
    private BigDecimal sumRemovalsFc; // (20,9)
    @ExcelColumn(name = "VTIME", index = 17)
    private Integer vTime;
    @ExcelColumn(name = "LOGISTIK", index = 18)
    private Integer logisticTime;
    @ExcelColumn(name = "TOLERANCE", index = 19)
    private Integer tolerance;
    @ExcelColumn(name = "AC_TYPE", index = 20)
    private String aircraftType; //acType
    @ExcelColumn(name = "MEM_FACTOR", index = 21)
    private Integer memFactor;
    @ExcelColumn(name = "FLAG_WTIME", index = 22, mapper = BooleanToXMapperImpl.class)
    private Boolean flagWTime;
    @ExcelColumn(name = "DEVCODE", index = 23)
    private String devCode;
    @ExcelColumn(name = "CRITICAL_MRPGR", index = 24)
    private String criticalMrpgr;
    @ExcelColumn(name = "ACSERIES", index = 25)
    private String acSeries;
    @ExcelColumn(name = "ENGMAT", index = 26)
    private String engmat;
    @ExcelColumn(name = "Manuell", index = 27, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean manual = false;
    @ExcelColumn(name = "Kennung", index = 28, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean identifier = false;
    @ExcelColumn(name = "FH_Kuenstlich", index = 29, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean fhArtificial = false;

    private String planningCycle;
    private Long projectId;
    private Long simulationId;
}
