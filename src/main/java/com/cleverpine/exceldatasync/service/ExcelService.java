package com.cleverpine.exceldatasync.service;

import com.cleverpine.exceldatasync.dto.ExcelFileDto;
import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;

public interface ExcelService {

    <S extends ExportExcelDto> SXSSFWorkbook updateExcelInChunks(SXSSFWorkbook workbook, List<S> exportExcelDtoList, Class<S> dtoClass);

    <S extends ExportExcelDto> SXSSFWorkbook updateExcel(SXSSFWorkbook workbook, List<S> exportExcelDtoList, Class<S> dtoClass);

    void importReferenceFile(ExcelFileDto excelFileDto);

}
