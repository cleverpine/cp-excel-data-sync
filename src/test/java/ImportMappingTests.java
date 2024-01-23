import com.cleverpine.exceldatasync.service.impl.ExcelImportConfigImpl;
import dto.ValueTypeTestDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.cleverpine.exceldatasync.util.Constants.BATCH_IMPORT_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImportMappingTests extends ImportTests {

    private final List<ValueTypeTestDto> testDtoList = new ArrayList<>();

    @BeforeEach
    void setup() {
        ExcelImportConfigImpl config = ExcelImportConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();
        excelImportService.importFrom(inputStream, ValueTypeTestDto.class, config, testDtoList::addAll);
    }

    @Test
    void booleanValues_shouldBeParsedCorrectly() {
        verifyBooleanMapping(0, true);
        verifyBooleanMapping(1, false);
        verifyBooleanMapping(2, true);
        verifyBooleanMapping(3, false);
        verifyBooleanMapping(4, true);
        verifyBooleanMapping(5, false);
        verifyBooleanMapping(6, true);
        verifyBooleanMapping(7, false);
        verifyBooleanMapping(8, true);
        verifyBooleanMapping(9, false);
    }

    @Test
    void doubleValues_shouldBeParsedCorrectly() {
        verifyDoubleMapping(0, 1.11111);
        verifyDoubleMapping(1, 2.22222);
        verifyDoubleMapping(2, 3.33333);
        verifyDoubleMapping(3, 4.44444);
        verifyDoubleMapping(4, 5.55555);
        verifyDoubleMapping(5, 6.66666);
        verifyDoubleMapping(6, 7.77777);
        verifyDoubleMapping(7, 8.88888);
        verifyDoubleMapping(8, 9.99999);
        verifyDoubleMapping(9, 10.12345);
    }

    @Test
    void integerValues_shouldBeParsedCorrectly() {
        verifyIntegerMapping(0, 100);
        verifyIntegerMapping(1, 200);
        verifyIntegerMapping(2, 300);
        verifyIntegerMapping(3, 400);
        verifyIntegerMapping(4, 500);
        verifyIntegerMapping(5, 600);
        verifyIntegerMapping(6, 700);
        verifyIntegerMapping(7, 800);
        verifyIntegerMapping(8, 900);
        verifyIntegerMapping(9, 1000);
    }

    @Test
    void stringValues_shouldBeParsedCorrectly() {
        verifyStringMapping(0, "TRUE:1.11111", "true", "100", "03/17/2204");
        verifyStringMapping(1, "FALSE:2.22222", "false", "200", "03/18/2204");
        verifyStringMapping(2, "TRUE:3.33333", "true", "300", "03/19/2204");
        verifyStringMapping(3, "FALSE:4.44444", "false", "400", "03/20/2204");
        verifyStringMapping(4, "TRUE:5.55555", "true", "500", "03/21/2204");
        verifyStringMapping(5, "FALSE:6.66666", "false", "600", "03/22/2204");
        verifyStringMapping(6, "TRUE:7.77777", "true", "700", "03/23/2204");
        verifyStringMapping(7, "FALSE:8.88888", "false", "800", "03/24/2204");
        verifyStringMapping(8, "TRUE:9.99999", "true", "900", "03/25/2204");
        verifyStringMapping(9, "FALSE:10.12345", "false", "1000", "03/26/2204");
    }

    @Test
    void booleanToXValues_shouldBeParsedCorrectly() {
        verifyCustomMapping(0, false);
        verifyCustomMapping(1, true);
        verifyCustomMapping(2, false);
        verifyCustomMapping(3, true);
        verifyCustomMapping(4, false);
        verifyCustomMapping(5, true);
        verifyCustomMapping(6, false);
        verifyCustomMapping(7, true);
        verifyCustomMapping(8, false);
        verifyCustomMapping(9, true);
    }

    @Test
    void longValues_shouldBeParsedCorrectly() {
        verifyLongMapping(0, 9223372036854730000L);
        verifyLongMapping(1, 2147483655L);
        verifyLongMapping(2, 2147483669L);
        verifyLongMapping(3, 2147483622L);
        verifyLongMapping(4, 2147483699L);
        verifyLongMapping(5, 2147483612L);
        verifyLongMapping(6, 2147483655L);
        verifyLongMapping(7, 2147483666L);
        verifyLongMapping(8, 2147483677L);
        verifyLongMapping(9, 9223372036854770000L);
    }

    private void verifyLongMapping(int index, long expected) {
        ValueTypeTestDto dto = testDtoList.get(index);
        assertEquals(expected, dto.getNumericLong());
        assertEquals(expected, dto.getFormulaLong());
        assertEquals(expected, dto.getStringLong());
    }

    private void verifyBooleanMapping(int index, boolean expected) {
        ValueTypeTestDto dto = testDtoList.get(index);
        assertEquals(expected, dto.isGeneralBoolean());
        assertEquals(expected, dto.isFormulaBoolean());
        assertEquals(expected, dto.getStringBoolean());
    }

    private void verifyDoubleMapping(int index, double expected) {
        ValueTypeTestDto dto = testDtoList.get(index);
        assertEquals(expected, dto.getNumericDouble());
        assertEquals(expected, dto.getFormulaDouble());
        assertEquals(expected, dto.getStringDouble());
    }

    private void verifyIntegerMapping(int index, int expected) {
        ValueTypeTestDto dto = testDtoList.get(index);
        assertEquals(expected, dto.getGeneralInteger());
        assertEquals(expected, dto.getFormulaInteger());
        assertEquals(expected, dto.getStringInteger());
    }

    private void verifyStringMapping(int index, String formulaAsString, String booleanAsString, String numericAsString, String dateAsString) {
        ValueTypeTestDto dto = testDtoList.get(index);
        assertEquals(formulaAsString, dto.getFormulaString());
        assertEquals(booleanAsString, dto.getBooleanString());
        assertEquals(dateAsString, dto.getDateString());
        assertEquals(numericAsString, dto.getNumericString());
    }

    private void verifyCustomMapping(int index, boolean expected) {
        ValueTypeTestDto dto = testDtoList.get(index);
        assertEquals(expected, dto.getCustomBoolean());
    }

}
