package com.cleverpine.exceldatasync.service.impl.write.basic;

import com.cleverpine.exceldatasync.service.api.write.ExportPageable;
import lombok.Getter;

@Getter
public class BasicExportPageable implements ExportPageable {

    private final int page;
    private final int size;

    public BasicExportPageable(int size) {
        this.page = 0;
        this.size = getValidSize(size);
    }

    public BasicExportPageable(int page, int size) {
        this.page = getValidPage(page);
        this.size = getValidSize(size);
    }

    public ExportPageable next() {
        return new BasicExportPageable(getPage() + 1, getSize());
    }

    private int getValidPage(int page) {
        return Math.max(page, 0);
    }

    private int getValidSize(int size) {
        return Math.max(size, 1);
    }
}
