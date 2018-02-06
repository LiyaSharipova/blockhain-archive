package com.github.liyasharipova.blockchain.archive.file.repostiory;

import com.github.liyasharipova.blockchain.archive.file.entity.FileEntity;
import org.springframework.data.repository.CrudRepository;

import java.nio.file.Path;

/**
 * Репозиторий для файлов
 */
public interface FileRepository extends CrudRepository<FileEntity, Long> {

    FileEntity findByFileName(String filename);
}