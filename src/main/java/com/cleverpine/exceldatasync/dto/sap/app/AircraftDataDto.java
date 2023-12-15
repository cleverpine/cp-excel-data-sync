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
@ExcelSheet(name = "ZMDLP_ME_111")
public class AircraftDataDto extends ExportExcelDto { //111

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "ACSERIES", index = 1)
    private String acSeries;
    @ExcelColumn(name = "ACTYPE", index = 2)
    private String acType;
}
