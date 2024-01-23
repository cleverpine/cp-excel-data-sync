package dto;

import com.cleverpine.exceldatasync.annotations.ExcelColumn;
import com.cleverpine.exceldatasync.annotations.ExcelMapper;
import com.cleverpine.exceldatasync.annotations.ExcelSheet;
import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.mapper.impl.BooleanToXMapperImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ExcelSheet(name = "Value_type_test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ValueTypeTestDto implements ExcelDto {

    @ExcelColumn(name = "id", letter = "A")
    private String id;

    // Boolean
    @ExcelColumn(name = "general_boolean", letter = "B")
    private boolean generalBoolean;
    @ExcelColumn(name = "formula_boolean", letter = "C")
    private boolean formulaBoolean;
    @ExcelColumn(name = "string_boolean", letter = "D")
    private Boolean stringBoolean;

    // Double
    @ExcelColumn(name = "numeric_double", letter = "E")
    private double numericDouble;
    @ExcelColumn(name = "formula_double", letter = "F")
    private double formulaDouble;
    @ExcelColumn(name = "string_double", letter = "G")
    private Double stringDouble;

    // Integer
    @ExcelColumn(name = "general_integer", letter = "H")
    private int generalInteger;
    @ExcelColumn(name = "formula_integer", letter = "I")
    private int formulaInteger;
    @ExcelColumn(name = "string_integer", letter = "J")
    private Integer stringInteger;

    // String
    @ExcelColumn(name = "formula_string", letter = "K")
    private String formulaString;
    @ExcelColumn(name = "boolean_string", letter = "B")
    private String booleanString;
    @ExcelColumn(name = "date_string", letter = "L")
    private String dateString;
    @ExcelColumn(name = "numeric_string", letter = "H")
    private String numericString;

    // Custom
    @ExcelColumn(name = "custom_boolean", letter = "M")
    @ExcelMapper(mapper = BooleanToXMapperImpl.class)
    private Boolean customBoolean;

    // Long
    @ExcelColumn(name = "numeric_long", letter = "N")
    private long numericLong;
    @ExcelColumn(name = "formula_long", letter = "O")
    private long formulaLong;
    @ExcelColumn(name = "string_long", letter = "P")
    private Long stringLong;


}
