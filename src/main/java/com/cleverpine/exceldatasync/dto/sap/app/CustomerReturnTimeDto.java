package com.cleverpine.exceldatasync.dto.sap.app;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_204_REF")
public class CustomerReturnTimeDto extends ExportExcelDto { //204

    private String mandt = "002";
    private String materialCycle;
    @ExcelColumn(name = "OPERATOR_3LC", index = 0)
    private String operatorCode; //operator3lc
    @ExcelColumn(name = "TRANSPORT_GRP", index = 1)
    private String transportGroup; //transportGrp
    @ExcelColumn(name = "BACKHAUL_TIME", index = 2)
    private Integer returnTime; //backhaulTime
    @ExcelColumn(name = "TIME_SUPPLY", index = 3)
    private Integer timeSupply;
    private Boolean manual = false;
}
