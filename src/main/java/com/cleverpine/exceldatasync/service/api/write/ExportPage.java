package com.cleverpine.exceldatasync.service.api.write;

import com.cleverpine.exceldatasync.dto.ExcelDto;

import java.util.List;

public interface ExportPage<Dto extends ExcelDto> {

    List<Dto> getContent();

    boolean hasNext();
}
