package com.cleverpine.exceldatasync.service.impl;


import com.cleverpine.exceldatasync.dto.ExcelFileDto;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.exception.ExcelException;
import com.cleverpine.exceldatasync.service.ExcelService;
import com.cleverpine.exceldatasync.util.Constants;
import com.cleverpine.exceldatasync.util.ExportExcelHelper;
import com.cleverpine.exceldatasync.util.ImportExcelHelper;
import com.github.pjfanning.xlsx.StreamingReader;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

    private final ExportExcelHelper exportExcelHelper;
    private final ImportExcelHelper importExcelHelper;

    // This method will be used to reduce memory usage when generating Excel files.
    // Use it when passed DTO list is too large to fit into memory.
    @Override
    public <S extends ExportExcelDto> SXSSFWorkbook updateExcelInChunks(SXSSFWorkbook workbook, List<S> exportExcelDtoList,
            Class<S> dtoClass) {
        var chunks = exportExcelHelper.splitIntoChunks(exportExcelDtoList);

        if (chunks == null || chunks.isEmpty()) {
            return workbook;
        }

        for (List<S> chunk : chunks) {
            if (chunk != null && !chunk.isEmpty()) {
                exportExcelHelper.writeDataToWorkbook(workbook, chunk, dtoClass);
            }
        }

        return workbook;
    }

    @Override
    public <S extends ExportExcelDto> SXSSFWorkbook updateExcel(SXSSFWorkbook workbook, List<S> exportExcelDtoList, Class<S> dtoClass) {
        exportExcelHelper.writeDataToWorkbook(workbook, exportExcelDtoList, dtoClass);
        return workbook;
    }

    @Override
    public void importReferenceFile(ExcelFileDto excelFileDto) {
        try (var workbook = initializeStreamingWorkbook(excelFileDto.getData())) {
            importExcelHelper.importReferenceFileDto(workbook);
        } catch (IOException e) {
            throw new ExcelException(Constants.FAILED_TO_CLOSE_WORKBOOK_ERROR_MESSAGE, e);
        }
    }

    private Workbook initializeWorkbook(byte[] byteArray) {
        try (var inputStream = new ByteArrayInputStream(byteArray)) {
            return new XSSFWorkbook(inputStream);
        } catch (Exception e) {
            throw new ExcelException(Constants.FAILED_TO_INITIALIZE_WORKBOOK_ERROR_MESSAGE, e);
        }
    }

    private Workbook initializeStreamingWorkbook(byte[] byteArray) {
        try (var inputStream = new ByteArrayInputStream(byteArray)) {
            return StreamingReader.builder()
                    .rowCacheSize(Constants.ROW_CACHE_SIZE)
                    .bufferSize(Constants.BUFFER_SIZE)
                    .open(inputStream);
        } catch (Exception e) {
            throw new ExcelException(Constants.FAILED_TO_INITIALIZE_WORKBOOK_ERROR_MESSAGE, e);
        }
    }

    private Map<String, Integer> getSheetNameToIndexMap(Workbook workbook) {
        var sheetNameToIndexMap = new HashMap<String, Integer>();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            String sheetName = workbook.getSheetName(i);
            sheetNameToIndexMap.put(sheetName, i);
        }

        return sheetNameToIndexMap;
    }
}
