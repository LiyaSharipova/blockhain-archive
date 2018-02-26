package com.github.liyasharipova.blockchain.archive.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.io.Resource;

/**
 * ДТО для отображения на фронте
 */
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Resource getFileData() {
        return fileData;
    }

    public void setFileData(Resource fileData) {
        this.fileData = fileData;
    }
}