package com.cleverpine.exceldatasync.dto.sap.app.optimization.header;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.mapper.impl.BooleanToUpperCaseMapperImpl;
import com.cleverpine.exceldatasync.mapper.impl.BooleanToXMapperImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_RMA_160_726")
public class WarehouseNodesDto extends ExportExcelDto { //726

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "MATERIAL_CYCLE", index = 1)
    private String materialCycle;
    @ExcelColumn(name = "PLC", index = 2)
    private String planningCycle;
    @ExcelColumn(name = "NID", index = 3)
    private String nodeId; //nid
    @ExcelColumn(name = "NAME", index = 4)
    private String name;
    @ExcelColumn(name = "REGION", index = 5)
    private String region;
    @ExcelColumn(name = "LOCATION_TYPE", index = 6)
    private String locationType;
    @ExcelColumn(name = "LOCATION_NO_HEAD", index = 7, mapper = BooleanToXMapperImpl.class)
    private Boolean noHeadLocation;
    @ExcelColumn(name = "Manuell", index = 8, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean manual = false;
}
