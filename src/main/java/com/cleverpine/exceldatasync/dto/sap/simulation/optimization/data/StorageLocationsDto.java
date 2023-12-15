package com.cleverpine.exceldatasync.dto.sap.simulation.optimization.data;

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
@ExcelSheet(name = "ERG_Lagerorte")
public class StorageLocationsDto extends ExportExcelDto {
    // TODO: fix this DTO when getting data from the table
    @ExcelColumn(name = "NID", index = 0)
    private String nodeId; //nid
    @ExcelColumn(name = "NAME", index = 1)
    private String name;
}
