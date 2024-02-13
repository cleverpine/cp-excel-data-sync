import com.cleverpine.exceldatasync.service.api.read.ExcelImportService;
import com.cleverpine.exceldatasync.service.impl.read.ExcelImportServiceImpl;
import java.io.InputStream;

public abstract class ImportTests {

    protected static final int TOTAL_ROWS = 115908;
    protected final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_file.xlsx");
    protected final ExcelImportService excelImportService = new ExcelImportServiceImpl();

}
