import com.cleverpine.exceldatasync.service.impl.read.ExcelImportConfigImpl;
import com.cleverpine.exceldatasync.service.impl.read.ExcelMultipleImportConfig;
import com.cleverpine.exceldatasync.service.impl.read.ExcelSheetImportConfig;
import dto.DocumentDto;
import dto.NoHeadersDocumentDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.cleverpine.exceldatasync.util.Constants.BATCH_IMPORT_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImportEmptyTests extends ImportTests {


    @Test
    void importEmptySheetWhenNoHeaders_shouldReturnEmptyList() {
        final List<NoHeadersDocumentDto> documents = new ArrayList<>();
        ExcelImportConfigImpl config = ExcelImportConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();
        excelImportService.importFrom(inputStream, new ExcelSheetImportConfig<>(NoHeadersDocumentDto.class, config, documents::addAll));

        assertEquals(0, documents.size());
    }

    @Test
    void importFromEmptyTemplateWhenNoItems_shouldReturnEmptyList() {
        final List<DocumentDto> documents = new ArrayList<>();
        ExcelImportConfigImpl config = ExcelImportConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();
        excelImportService.importFrom(inputStream, new ExcelSheetImportConfig<>(DocumentDto.class, config, documents::addAll));

        assertEquals(0, documents.size());
    }

    @Test
    void multipleImportEmptySheetWhenNoHeaders_shouldReturnEmptyList() {
        final List<NoHeadersDocumentDto> documents = new ArrayList<>();
        ExcelImportConfigImpl config = ExcelImportConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();
        ExcelMultipleImportConfig multipleConfig =
                new ExcelMultipleImportConfig(List.of(new ExcelSheetImportConfig<>(NoHeadersDocumentDto.class, config, documents::addAll)));
        excelImportService.importFrom(inputStream, multipleConfig);

        assertEquals(0, documents.size());
    }

    @Test
    void multipleImportFromEmptyTemplateWhenNoItems_shouldReturnEmptyList() {
        final List<DocumentDto> documents = new ArrayList<>();
        ExcelImportConfigImpl config = ExcelImportConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();
        ExcelMultipleImportConfig multipleConfig =
                new ExcelMultipleImportConfig(List.of(new ExcelSheetImportConfig<>(DocumentDto.class, config, documents::addAll)));
        excelImportService.importFrom(inputStream, multipleConfig);

        assertEquals(0, documents.size());
    }
}
