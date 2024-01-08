package dto;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExcelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ExcelSheet(name = "Sheet2")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EngineDto implements ExcelDto {

    @ExcelColumn(name = "id", letter = "A")
    private String id;
    @ExcelColumn(name = "service_category", letter = "L")
    private String category;
    @ExcelColumn(name = "primary_engine_type_spec", letter = "N")
    private String type;
    @ExcelColumn(name = "primary_engine_version_spec", letter = "O")
    private String version;

}
