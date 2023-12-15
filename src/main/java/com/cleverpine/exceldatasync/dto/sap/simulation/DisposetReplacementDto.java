package com.cleverpine.exceldatasync.dto.sap.simulation;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.dto.sap.contract.ProjectIdHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ExcelSheet(name = "ZMDLP_307_REF")
public class DisposetReplacementDto extends ExportExcelDto implements ProjectIdHandler { //307

    @ExcelColumn(name = "MANDT", index = 0)
    private String mandt = "002";
    @ExcelColumn(name = "MATERIAL_CYCLE", index = 1)
    private String materialCycle;
    @ExcelColumn(name = "MRPGR", index = 2)
    private String disposetNumber; //mrpgr
    @ExcelColumn(name = "MRPGR_DIS", index = 3)
    private String replacementDisposetNumber; //mrpgrDis
    @ExcelColumn(name = "EO_TYPE", index = 4)
    private String eoType;

    private OffsetDateTime datab;
    private OffsetDateTime datbi;
    private String info;
    private Long projectId;
    private Long simulationId;
}
