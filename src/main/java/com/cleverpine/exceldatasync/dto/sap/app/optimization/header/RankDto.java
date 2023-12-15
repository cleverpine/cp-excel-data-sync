package com.cleverpine.exceldatasync.dto.sap.app.optimization.header;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.mapper.impl.BooleanToUpperCaseMapperImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_RMA_106_723")
public class RankDto extends ExportExcelDto { //723

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "NID", index = 1)
    private String nodeId; //nid
    @ExcelColumn(name = "RANG", index = 2)
    private Integer rank;
    @ExcelColumn(name = "PENALTY_COST", index = 3)
    private BigDecimal penaltyCost; // (16,2)
    @ExcelColumn(name = "Manuell", index = 4, mapper = BooleanToUpperCaseMapperImpl.class)
    private Boolean manual = false;
}
