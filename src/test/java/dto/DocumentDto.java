package dto;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExcelDto;
import lombok.*;

@ExcelSheet(name = "Empty template", startingRow = 4)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DocumentDto implements ExcelDto {

    @ExcelColumn(name = "Doc. Type", letter = "A")
    private String type;

    @ExcelColumn(name = "Doc. Number", letter = "B")
    private String number;

    @ExcelColumn(name = "Title", letter = "C")
    private String title;

    @ExcelColumn(name = "Revision", letter = "D")
    private Integer revision;
}
