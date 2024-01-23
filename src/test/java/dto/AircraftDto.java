package dto;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExcelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ExcelSheet(name = "Custom_starting_row", startingRow = 900000)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AircraftDto implements ExcelDto {

    @ExcelColumn(name = "id", letter = "XEO")
    private String id;
    @ExcelColumn(name = "msn", letter = "XEV")
    private String msn;
    @ExcelColumn(name = "production_number", letter = "XEW")
    private String productionNumber;
    @ExcelColumn(name = "line_number", letter = "XEX")
    private String lineNumber;

}
