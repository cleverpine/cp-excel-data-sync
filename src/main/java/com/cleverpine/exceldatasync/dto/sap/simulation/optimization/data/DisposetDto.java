package com.cleverpine.exceldatasync.dto.sap.simulation.optimization.data;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.dto.sap.contract.ProjectIdHandler;
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
@ExcelSheet(name = "ZMDLP_300_RMA_730")
public class DisposetDto extends ExportExcelDto implements ProjectIdHandler { //730

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "MATERIAL_CYCLE", index = 1)
    private String materialCycle;
    @ExcelColumn(name = "MRPGR", index = 2)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "FLAG_WTIME", index = 3, mapper = BooleanToXMapperImpl.class)
    private Boolean flagWtime;
    @ExcelColumn(name = "PERC_WTIME_CHG", index = 4)
    private BigDecimal percWtimeChg; // (6,2)
    @ExcelColumn(name = "FLAG_HTIME", index = 5, mapper = BooleanToXMapperImpl.class)
    private Boolean flagHtime;
    @ExcelColumn(name = "PERC_HTIME_CHG", index = 6)
    private BigDecimal percHtimeChg; // (6,2)
    @ExcelColumn(name = "RTY", index = 7)
    private String transportGroup; //rty
    @ExcelColumn(name = "SCRAP_DETECTION", index = 8)
    private Integer scrapDetection;
    @ExcelColumn(name = "LOTSIZE_REPROVIS", index = 9)
    private Integer lotsizeReprovision;
    @ExcelColumn(name = "QTY_SHIPSET", index = 10)
    private Integer shipsetSizeStd;
    @ExcelColumn(name = "QTY_SHIPSET_LAYO", index = 11)
    private Integer shipsetSizeLayo;
    @ExcelColumn(name = "BACKHAUL_T_LAYO", index = 12)
    private Integer backhaulTLayo;
    @ExcelColumn(name = "SEASON_FACTOR", index = 13)
    private BigDecimal seasonFactor; // (5,3)
    @ExcelColumn(name = "SEASON_FLAG", index = 14, mapper = BooleanToXMapperImpl.class)
    private Boolean seasonFlag;
    @ExcelColumn(name = "FLAG_NO_DEMAND", index = 15, mapper = BooleanToXMapperImpl.class)
    private Boolean flagNoDemand;
    @ExcelColumn(name = "PRICE_MOVING_AV", index = 16)
    private BigDecimal priceGld; // (11,2)
    @ExcelColumn(name = "PRICE_NEW_UNIT", index = 17)
    private BigDecimal priceNewUnit; // (13,2)
    @ExcelColumn(name = "TIME_REPROVISION", index = 18)
    private Integer timeReprovision;
    @ExcelColumn(name = "MEM_FACTOR", index = 19)
    private BigDecimal memFactor; // (31,14)
    @ExcelColumn(name = "DSS_EW_KEY", index = 20)
    private String dssEwKey;
    @ExcelColumn(name = "DSS_EW_DESCR", index = 21)
    private String dssEwDescr;
    @ExcelColumn(name = "VERPR", index = 22)
    private BigDecimal verpr;
    @ExcelColumn(name = "LOCAL_MRO_EM", index = 23, mapper = BooleanToXMapperImpl.class)
    private Boolean localMroEm;
    @ExcelColumn(name = "LOCAL_MRO_AM", index = 24, mapper = BooleanToXMapperImpl.class)
    private Boolean localMroAm;
    @ExcelColumn(name = "LOCAL_MRO_AP", index = 25, mapper = BooleanToXMapperImpl.class)
    private Boolean localMroAp;
    @ExcelColumn(name = "COSTFUNCTION_ID", index = 26)
    private String costFunctionId;
    @ExcelColumn(name = "CRITICAL_MRPGR", index = 27, mapper = BooleanToXMapperImpl.class)
    private Boolean criticalDisposetNumber; //criticalMrpgr
    @ExcelColumn(name = "NBZ", index = 28)
    private Integer nbz;
    @ExcelColumn(name = "SCR", index = 29)
    private BigDecimal scrapRate; // (10,6)
    @ExcelColumn(name = "STRUCTURE_ID", index = 30)
    private String structureId;
    @ExcelColumn(name = "STRUCTURE_ID1", index = 31)
    private String structureId1;
    @ExcelColumn(name = "STRUCTURE_ID2", index = 32)
    private String structureId2;
    @ExcelColumn(name = "DEVCODE", index = 33)
    private String deviationCode; //devCode
    @ExcelColumn(name = "ORIGINAL_PRICE", index = 34)
    private BigDecimal originalPrice;
    @ExcelColumn(name = "DIREKT", index = 35, mapper = BooleanToXMapperImpl.class)
    private Boolean direkt;

    private String computationId;
    private Long projectId;
    private Long simulationId;
}
