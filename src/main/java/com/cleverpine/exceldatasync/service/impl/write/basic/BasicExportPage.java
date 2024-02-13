package com.cleverpine.exceldatasync.service.impl.write.basic;

import com.cleverpine.exceldatasync.dto.ExcelDto;
import com.cleverpine.exceldatasync.service.api.write.ExportPage;
import lombok.Getter;

import java.util.List;

public class BasicExportPage<Dto extends ExcelDto> implements ExportPage<Dto> {

    @Getter
    private final List<Dto> content;
    private final boolean hasNext;

    public BasicExportPage(List<Dto> content, boolean hasNext) {
        this.content = content;
        this.hasNext = hasNext;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }
}
