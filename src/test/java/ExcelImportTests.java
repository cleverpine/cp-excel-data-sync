import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.service.api.ExcelConfig;
import com.cleverpine.exceldatasync.service.api.ExcelImportService;
import com.cleverpine.exceldatasync.service.impl.ExcelConfigImpl;
import com.cleverpine.exceldatasync.service.impl.ExcelImportServiceImpl;
import dto.AircraftDto;
import dto.EngineDto;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.cleverpine.exceldatasync.util.Constants.BATCH_IMPORT_SIZE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcelImportTests {

    private static final int TOTAL_ROWS = 115908;
    private final ExcelImportService excelImportService = new ExcelImportServiceImpl();

    private InputStream inputStream;
    private List<Integer> batchSizes;

    private List<AircraftDto> firstAircraftElements;
    private List<AircraftDto> lastAircraftElements;

    private List<EngineDto> firstEngineElements;
    private List<EngineDto> lastEngineElements;

    @BeforeEach
    void setup() {
        inputStream = getClass().getClassLoader().getResourceAsStream("test_file.xlsx");
        batchSizes = new ArrayList<>();

        firstAircraftElements = new ArrayList<>();
        lastAircraftElements = new ArrayList<>();

        firstEngineElements = new ArrayList<>();
        lastEngineElements = new ArrayList<>();
    }

    @Test
    void excelImport_shouldExtractAircraftData_withDefaultBatchSize_fromCustomSheetCoordinates() {
        ExcelConfigImpl config = ExcelConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();

        long startTime = System.currentTimeMillis();
        excelImportService.importFrom(inputStream, AircraftDto.class, config, this::processAircraft);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf("Execution time: %d ms\n", executionTime);

        assertAll(
                () -> assertNumberOfRows(),
                () -> assertNumberOfBatches(config),
                () -> verifyBatch(config, firstAircraftElements, lastAircraftElements)
        );
    }

    @Test
    void excelImport_shouldExtractEngineData_withCustomBatchSize_fromDefaultSheetCoordinates() {
        ExcelConfigImpl config = ExcelConfigImpl.builder().batchSize(5000).build();

        long startTime = System.currentTimeMillis();
        excelImportService.importFrom(inputStream, EngineDto.class, config, this::processEngines);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf("Execution time: %d ms\n", executionTime);

        assertAll(
                () -> assertNumberOfRows(),
                () -> assertNumberOfBatches(config),
                () -> verifyBatch(config, firstEngineElements, lastEngineElements)
        );
    }

    private void processAircraft(List<AircraftDto> batch) {
        batchSizes.add(batch.size());

        AircraftDto firstElement = batch.get(0);
        firstAircraftElements.add(firstElement);

        AircraftDto lastElement = batch.get(batch.size() - 1);
        lastAircraftElements.add(lastElement);
    }

    private void processEngines(List<EngineDto> batch) {
        batchSizes.add(batch.size());

        EngineDto firstElement = batch.get(0);
        firstEngineElements.add(firstElement);

        EngineDto lastElement = batch.get(batch.size() - 1);
        lastEngineElements.add(lastElement);
    }

    private void assertNumberOfRows() {
        int numberOfRows = batchSizes.stream().reduce(0, Integer::sum);
        assertEquals(TOTAL_ROWS, numberOfRows);
    }

    private void assertNumberOfBatches(ExcelConfig config) {
        int expectedBatches = (int) Math.ceil((double) TOTAL_ROWS / config.getBatchSize());
        int numberOfBatches = batchSizes.size();
        assertEquals(expectedBatches, numberOfBatches);
    }

    private <Dto extends ExcelDto> void verifyBatch(ExcelConfig config, List<Dto> firstElements, List<Dto> lastElements) {
        int firstElementId = 1;
        int lastElementId;

        for (int i = 0; i < batchSizes.size(); i++) {
            Dto firstElement = firstElements.get(i);
            Dto lastElement = lastElements.get(i);

            String firstId = null;
            String lastId = null;

            try {
                firstId = (String) ((Object) firstElement).getClass().getMethod("getId").invoke(firstElement);
                lastId = (String) ((Object) lastElement).getClass().getMethod("getId").invoke(lastElement);
            } catch (Exception ignored) {
            }

            if (i != batchSizes.size() - 1) {
                lastElementId = firstElementId + config.getBatchSize() - 1;
            } else {
                lastElementId = TOTAL_ROWS;
            }

            assertEquals(firstElementId + ":" + lastElementId, firstId + ":" + lastId);
            assertEquals(Integer.parseInt(lastId) - Integer.parseInt(firstId) + 1, batchSizes.get(i));

            firstElementId += config.getBatchSize();
        }
    }
}
