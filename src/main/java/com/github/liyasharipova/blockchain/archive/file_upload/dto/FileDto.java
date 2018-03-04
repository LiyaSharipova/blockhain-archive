package com.github.liyasharipova.blockchain.archive.file_upload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.core.io.Resource;

/**
 * ДТО для отображения на фронте
 */
@Data
public class FileDto {

    @JsonProperty(value = "file_name")
    private String fileName;

    @JsonProperty(value = "file_data")
    private Resource fileData;

    public FileDto() {
    }

    public FileDto(String fileName, Resource fileData)  {
        this.fileName = fileName;
        this.fileData = fileData;
    }

}