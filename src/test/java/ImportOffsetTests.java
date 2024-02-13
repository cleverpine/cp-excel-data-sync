import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.exception.ExcelException;
import com.cleverpine.exceldatasync.service.api.read.ExcelImportConfig;
import com.cleverpine.exceldatasync.service.impl.read.ExcelImportConfigImpl;
import com.cleverpine.exceldatasync.service.impl.read.ExcelMultipleImportConfig;
import com.cleverpine.exceldatasync.service.impl.read.ExcelSheetImportConfig;
import dto.AircraftDto;
import dto.EngineDto;
import dto.NoHeaderDto;
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
        ExcelImportConfigImpl config = ExcelImportConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();

        var processAircraft = createAircraftDtoListConsumer();

        measureExecutionTime(
                () -> excelImportService.importFrom(inputStream, createExcelSheetImportConfig(AircraftDto.class, config, processAircraft)));

        assertAll(
                this::assertNumberOfRows,
                () -> assertNumberOfBatches(config),
                () -> verifyBatch(config, firstAircraftElements, lastAircraftElements)
        );
    }

    @Test
    void sheetWithDefaultCoordinates_shouldImportWithCustomBatchSize() {
        ExcelImportConfigImpl config = ExcelImportConfigImpl.builder().batchSize(5000).build();

        var processEngines = createEngineDtoListConsumer();

        measureExecutionTime(
                () -> excelImportService.importFrom(inputStream, createExcelSheetImportConfig(EngineDto.class, config, processEngines)));

        assertAll(
                this::assertNumberOfRows,
                () -> assertNumberOfBatches(config),
                () -> verifyBatch(config, firstEngineElements, lastEngineElements)
        );
    }

    @Test
    void sheetNoHeadersAndStartPositionBeforeNonRelevantDtoData_thenThrowsExcelException() {
        assertThrows(ExcelException.class, () -> {
            ExcelImportConfigImpl config = ExcelImportConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();
            excelImportService.importFrom(inputStream, createExcelSheetImportConfig(NoHeaderDto.class, config, (batch) -> {
            }));
        });
    }

    @Test
    void multipleImport_onSheetWithCustomCoordinates_shouldImportWithDefaultBatchSize() {
        ExcelImportConfigImpl config = ExcelImportConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();

        var processAircraft = createAircraftDtoListConsumer();

        ExcelMultipleImportConfig multipleConfig =
                new ExcelMultipleImportConfig(List.of(createExcelSheetImportConfig(AircraftDto.class, config, processAircraft)));

        measureExecutionTime(
                () -> excelImportService.importFrom(inputStream, multipleConfig));

        assertAll(
                this::assertNumberOfRows,
                () -> assertNumberOfBatches(config),
                () -> verifyBatch(config, firstAircraftElements, lastAircraftElements)
        );
    }

    @Test
    void multipleImport_onSheetWithDefaultCoordinates_shouldImportWithCustomBatchSize() {
        ExcelImportConfigImpl config = ExcelImportConfigImpl.builder().batchSize(5000).build();

        var processEngines = createEngineDtoListConsumer();

        ExcelMultipleImportConfig multipleConfig =
                new ExcelMultipleImportConfig(List.of(createExcelSheetImportConfig(EngineDto.class, config, processEngines)));

        measureExecutionTime(
                () -> excelImportService.importFrom(inputStream, multipleConfig));

        assertAll(
                this::assertNumberOfRows,
                () -> assertNumberOfBatches(config),
                () -> verifyBatch(config, firstEngineElements, lastEngineElements)
        );
    }

    @Test
    void multipleImport_onSheetNoHeadersAndStartPositionBeforeNonRelevantDtoData_thenThrowsExcelException() {
        assertThrows(ExcelException.class, () -> {
            ExcelImportConfigImpl config = ExcelImportConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();
            ExcelMultipleImportConfig multipleConfig =
                    new ExcelMultipleImportConfig(List.of(createExcelSheetImportConfig(NoHeaderDto.class, config, (batch) -> {
                    })));
            excelImportService.importFrom(inputStream, multipleConfig);
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

    private void assertNumberOfBatches(ExcelImportConfig config) {
        int expectedBatches = (int) Math.ceil((double) TOTAL_ROWS / config.getBatchSize());
        int numberOfBatches = batchSizes.size();
        assertEquals(expectedBatches, numberOfBatches);
    }

    private <Dto extends ExcelDto> void verifyBatch(ExcelImportConfig config, List<Dto> firstElements, List<Dto> lastElements) {
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

    private Consumer<List<AircraftDto>> createAircraftDtoListConsumer() {
        return batch -> {
            batchSizes.add(batch.size());
            AircraftDto firstElement = batch.get(0);
            firstAircraftElements.add(firstElement);
            AircraftDto lastElement = batch.get(batch.size() - 1);
            lastAircraftElements.add(lastElement);
        };
    }

    private Consumer<List<EngineDto>> createEngineDtoListConsumer() {
        return batch -> {
            batchSizes.add(batch.size());
            EngineDto firstElement = batch.get(0);
            firstEngineElements.add(firstElement);
            EngineDto lastElement = batch.get(batch.size() - 1);
            lastEngineElements.add(lastElement);
        };
    }

    private <Dto extends ExcelDto> ExcelSheetImportConfig<Dto> createExcelSheetImportConfig(Class<Dto> dtoClass,
                                                                                            ExcelImportConfigImpl config,
                                                                                            Consumer<List<Dto>> batchConsumer) {
        return new ExcelSheetImportConfig<>(dtoClass, config, batchConsumer);
    }
}
