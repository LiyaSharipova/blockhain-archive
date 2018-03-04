package com.github.liyasharipova.blockchain.archive.file_tofix.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    /**
     * Положить файл в хранилище файл,
     * который был загружен частями за один раз
     * @param file Части одного файла
     */
    void store(MultipartFile file);

    List<Resource> loadAll();

    byte[] load(String filename);

    Resource loadAsResource(String filename);

}
