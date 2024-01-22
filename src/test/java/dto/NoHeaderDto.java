package dto;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExcelDto;
import lombok.*;

@ExcelSheet(name = "Empty no header irrelevant data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NoHeaderDto implements ExcelDto {

    @ExcelColumn(name = "", letter = "A")
    private String type;

    @ExcelColumn(name = "", letter = "B")
    private String number;
}
