package com.cleverpine.exceldatasync.dto.sap.app.optimization.header;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.mapper.impl.BooleanToUpperCaseMapperImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_ME_423_RMA_728")
public class CustomerDefaultLogisticsPlanDataDto extends ExportExcelDto { //728

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "MATERIAL_CYCLE", index = 1)
    private String materialCycle;
    @ExcelColumn(name = "NID", index = 2)
    private String nodeId; //nid
    @ExcelColumn(name = "OPERATOR", index = 3)
    private String operator;
    @ExcelColumn(name = "TRANS_TIME_STD", index = 4)
    private Integer transTimeStd;
    @ExcelColumn(name = "TRANS_TIME_GBT", index = 5)
    private Integer transTimeGbt;
    @ExcelColumn(name = "TRANS_TIME_DGR", index = 6)
    private Integer transTimeDgr;
    @ExcelColumn(name = "TRANS_TIME_VB", index = 7)
    private Integer transTimeVb;
    @ExcelColumn(name = "Manuell", index = 8, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean manual = false;
}
