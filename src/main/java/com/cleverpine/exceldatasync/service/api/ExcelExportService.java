package com.cleverpine.exceldatasync.service.api;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

public interface ExcelExportService {

    <T> InputStream exportToStream(Iterable<T> dtoList, Class<T> dtoClass);
}
