package com.cleverpine.exceldatasync.service.api;

import com.cleverpine.exceldatasync.dto.ExcelDto;

import java.util.List;

public interface ExportPageable<Dto extends ExcelDto> {

    List<Dto> getPageData();

    ExportPageable<Dto> next();

    boolean hasNext();

}
