package com.cleverpine.exceldatasync.dto;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "Glossar")
public class SimulationReferenceGlossaryDto extends ExportExcelDto {

    @ExcelColumn(name = "QuelleName", index = 0)
    private String sourceName;
    @ExcelColumn(name = "Klarname", index = 1)
    private String clearName;
    @ExcelColumn(name = "Bedeutung", index = 2)
    private String meaning;
}
