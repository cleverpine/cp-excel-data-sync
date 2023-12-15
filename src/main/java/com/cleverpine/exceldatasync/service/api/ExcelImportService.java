package com.cleverpine.exceldatasync.service.api;

import java.io.InputStream;
import java.util.List;

public interface ExcelImportService {

    <T> Iterable<T> importFromStream(InputStream inputStream, List<Class<T>> dtoClasses);
}
