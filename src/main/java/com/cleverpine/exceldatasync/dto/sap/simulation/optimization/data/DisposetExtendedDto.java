package com.cleverpine.exceldatasync.dto.sap.simulation.optimization.data;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
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
@ExcelSheet(name = "ERG_MRPGR")
public class DisposetExtendedDto extends ExportExcelDto {

    @ExcelColumn(name = "MRPGR", index = 0)
    private String disposetNumber; //mrpgr
    private String tid;
    private Boolean flagMultistage;
    @ExcelColumn(name = "FLAG_WTIME", index = 1, mapper = BooleanToXMapperImpl.class)
    private Boolean flagWtime;
    @ExcelColumn(name = "PERC_WTIME_CHG", index = 2)
    private BigDecimal percWtimeChg; // (6,2)
    @ExcelColumn(name = "FLAG_HTIME", index = 3, mapper = BooleanToXMapperImpl.class)
    private Boolean flagHtime;
    @ExcelColumn(name = "PERC_HTIME_CHG", index = 4)
    private BigDecimal percHtimeChg; // (6,2)
    @ExcelColumn(name = "RTY", index = 5)
    private String transportGroup; //rty
    @ExcelColumn(name = "SCRAP_DETECTION", index = 6)
    private Integer scrapDetection;
    @ExcelColumn(name = "NBZ", index = 7)
    private Integer nbz;
    @ExcelColumn(name = "SCR", index = 8)
    private BigDecimal scr;
    private String bOption;
    @ExcelColumn(name = "LOTSIZE_REPROVIS", index = 9)
    private Integer lotsizeReprovision;
    @ExcelColumn(name = "QTY_SHIPSET", index = 10)
    private Integer qtyShipset;
    @ExcelColumn(name = "QTY_SHIPSET_LAYO", index = 11)
    private Integer qtyShipsetLayo;
    @ExcelColumn(name = "BACKHAUL_T_LAYO", index = 12)
    private Integer backhaulTLayo;
    private String lvorm;
    @ExcelColumn(name = "SEASON_FACTOR", index = 13)
    private BigDecimal seasonFactor; // (5,3)
    @ExcelColumn(name = "SEASON_FLAG", index = 14, mapper = BooleanToXMapperImpl.class)
    private Boolean seasonFlag;
    private Boolean calcRel;
    @ExcelColumn(name = "FLAG_NO_DEMAND", index = 15, mapper = BooleanToXMapperImpl.class)
    private Boolean flagNoDemand;
    @ExcelColumn(name = "MEM_FACTOR", index = 16)
    private BigDecimal memFactor; // (31,14)
    @ExcelColumn(name = "STRUCTURE_ID", index = 17)
    private String structureId;
    @ExcelColumn(name = "LOCAL_MRO_EM", index = 18, mapper = BooleanToXMapperImpl.class)
    private Boolean localMroEm;
    @ExcelColumn(name = "LOCAL_MRO_AM", index = 19, mapper = BooleanToXMapperImpl.class)
    private Boolean localMroAm;
    @ExcelColumn(name = "LOCAL_MRO_AP", index = 20, mapper = BooleanToXMapperImpl.class)
    private Boolean localMroAp;
    @ExcelColumn(name = "CRITICAL_MRPGR", index = 21, mapper = BooleanToXMapperImpl.class)
    private Boolean criticalDisposetNumber; //criticalMrpgr
    @ExcelColumn(name = "COSTFUNCTION_ID", index = 22)
    private String costFunctionId;
    @ExcelColumn(name = "MRPGR_STATUS", index = 23)
    private String disposetNumberStatus;
    private BigDecimal verpr; //pricegld
    @ExcelColumn(name = "ORIGINAL_PRICE", index = 24)
    private BigDecimal originalPrice;
    private String dateMod1;
    private String dateMod2;
    @ExcelColumn(name = "DEVCODE", index = 25)
    private String deviationCode; //devCode
    @ExcelColumn(name = "STRUCTURE_ID1", index = 26)
    private String structureId1;
    @ExcelColumn(name = "STRUCTURE_ID2", index = 27)
    private String structureId2;
    private Integer reprovisionTime;
    private String lmatn;
    private String lmatnExt;
    @ExcelColumn(name = "PRICE_MOVING_AV", index = 28)
    private BigDecimal priceMovingAv;
    @ExcelColumn(name = "PRICE_NEW_UNIT", index = 29)
    private BigDecimal priceNewUnit; // (13,2)
    @ExcelColumn(name = "TIME_REPROVISION", index = 30)
    private Integer timeReprovision;
    @ExcelColumn(name = "DSS_EW_KEY", index = 31)
    private String dssEwKey;
    @ExcelColumn(name = "DSS_EW_DESCR", index = 32)
    private String dssEwDescr;
    @ExcelColumn(name = "MAX_QPA", index = 33)
    private String maxQpa;
    @ExcelColumn(name = "EW", index = 34)
    private String ew;
    private String customized;
    private String ess;
    private String hb;
    @ExcelColumn(name = "SHARE_LOGIC", index = 35)
    private String shareLogic;
    @ExcelColumn(name = "NUM_DSS_EW_SCHLUESSEL", index = 36)
    private String numDssEwSchluessel;
    @ExcelColumn(name = "SHARE_DSS_EW_SCHLUESSEL", index = 37)
    private String shareDssEwSchluessel;
    @ExcelColumn(name = "GemBev", index = 38, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean gemBev;
    @ExcelColumn(name = "TRANSPORT_TIME", index = 39)
    private Integer transportTime;
    @ExcelColumn(name = "TAT_TOT", index = 40)
    private BigDecimal tatTot;
}
