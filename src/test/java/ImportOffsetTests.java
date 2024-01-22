import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.exception.ExcelException;
import com.cleverpine.exceldatasync.service.api.ExcelConfig;
import com.cleverpine.exceldatasync.service.impl.ExcelConfigImpl;
import dto.AircraftDto;
import dto.EngineDto;
import dto.NoHeaderDto;
import dto.NoHeadersDocumentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.cleverpine.exceldatasync.util.Constants.BATCH_IMPORT_SIZE;
import static org.junit.jupiter.api.Assertions.*;

public class ImportOffsetTests extends ImportTests {

    private List<Integer> batchSizes;

    private List<AircraftDto> firstAircraftElements;
    private List<AircraftDto> lastAircraftElements;

    private List<EngineDto> firstEngineElements;
    private List<EngineDto> lastEngineElements;

    @BeforeEach
    void setup() {
        batchSizes = new ArrayList<>();
        firstAircraftElements = new ArrayList<>();
        lastAircraftElements = new ArrayList<>();
        firstEngineElements = new ArrayList<>();
        lastEngineElements = new ArrayList<>();
    }

    @Test
    void sheetWithCustomCoordinates_shouldImportWithDefaultBatchSize() {
        ExcelConfigImpl config = ExcelConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();

        Consumer<List<AircraftDto>> processAircraft = batch -> {
            batchSizes.add(batch.size());
            AircraftDto firstElement = batch.get(0);
            firstAircraftElements.add(firstElement);
            AircraftDto lastElement = batch.get(batch.size() - 1);
            lastAircraftElements.add(lastElement);
        };

        measureExecutionTime(
                () -> excelImportService.importFrom(inputStream, AircraftDto.class, config, processAircraft));

        assertAll(
                this::assertNumberOfRows,
                () -> assertNumberOfBatches(config),
                () -> verifyBatch(config, firstAircraftElements, lastAircraftElements)
        );
    }

    @Test
    void sheetWithDefaultCoordinates_shouldImportWithCustomBatchSize() {
        ExcelConfigImpl config = ExcelConfigImpl.builder().batchSize(5000).build();

        Consumer<List<EngineDto>> processEngines = batch -> {
            batchSizes.add(batch.size());
            EngineDto firstElement = batch.get(0);
            firstEngineElements.add(firstElement);
            EngineDto lastElement = batch.get(batch.size() - 1);
            lastEngineElements.add(lastElement);
        };

        measureExecutionTime(
                () -> excelImportService.importFrom(inputStream, EngineDto.class, config, processEngines));

        assertAll(
                this::assertNumberOfRows,
                () -> assertNumberOfBatches(config),
                () -> verifyBatch(config, firstEngineElements, lastEngineElements)
        );
    }

    @Test
    void sheetNoHeadersAndStartPositionBeforeNonRelevantDtoData_thenThrowsExcelException() {
        assertThrows(ExcelException.class, () -> {
            ExcelConfigImpl config = ExcelConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();
            excelImportService.importFrom(inputStream, NoHeaderDto.class, config, (batch) -> {
            });
        });
    }

    private void measureExecutionTime(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.printf("Execution time: %d ms\n", executionTime);
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
