import com.cleverpine.exceldatasync.service.impl.ExcelConfigImpl;
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
        ExcelConfigImpl config = ExcelConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();
        excelImportService.importFrom(inputStream, NoHeadersDocumentDto.class, config, documents::addAll);

        assertEquals(0, documents.size());
    }

    @Test
    void importFromEmptyTemplateWhenNoItems_shouldReturnEmptyList() {
        final List<DocumentDto> documents = new ArrayList<>();
        ExcelConfigImpl config = ExcelConfigImpl.builder().batchSize(BATCH_IMPORT_SIZE).build();
        excelImportService.importFrom(inputStream, DocumentDto.class, config, documents::addAll);

        assertEquals(0, documents.size());
    }
}
