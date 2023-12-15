package com.cleverpine.exceldatasync.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExcelFileDto extends FileDto {
    public ExcelFileDto(byte[] fileByteArray, String originalFilename) {
        super(fileByteArray, originalFilename);
    }
}
