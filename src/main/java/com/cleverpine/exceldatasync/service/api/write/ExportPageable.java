package com.cleverpine.exceldatasync.service.api.write;

public interface ExportPageable {

    int getPage();

    int getSize();

    ExportPageable next();
}
