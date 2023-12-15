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
@ExcelSheet(name = "ZMDLP_RMA_165_725")
public class TransportTimeBwWorkshopsDto extends ExportExcelDto { //725

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "MATERIAL_CYCLE", index = 1)
    private String materialCycle;
    @ExcelColumn(name = "NID_SOURCE", index = 2)
    private String sourceNodeId; //nidSource
    @ExcelColumn(name = "NID_TARGET", index = 3)
    private String targetNodeId; //nidTarget
    @ExcelColumn(name = "RTY", index = 4)
    private String transportGroup; //rty
    @ExcelColumn(name = "TRANSPORT_TIME", index = 5)
    private Integer transportTimeInMins; //transportTime
    @ExcelColumn(name = "Manuell", index = 6, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean manual = false;
}
