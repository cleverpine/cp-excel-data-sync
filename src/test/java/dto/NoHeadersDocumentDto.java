package dto;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExcelDto;
import lombok.*;

@ExcelSheet(name = "Empty Sheet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NoHeadersDocumentDto implements ExcelDto {

    @ExcelColumn(name = "", letter = "A")
    private String type;

    @ExcelColumn(name = "", letter = "B")
    private String number;

    @ExcelColumn(name = "", letter = "C")
    private String title;

    @ExcelColumn(name = "", letter = "D")
    private Integer revision;
}
