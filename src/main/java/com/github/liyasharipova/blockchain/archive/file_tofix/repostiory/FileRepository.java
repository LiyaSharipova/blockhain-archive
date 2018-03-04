package com.github.liyasharipova.blockchain.archive.file_tofix.repostiory;

import com.github.liyasharipova.blockchain.archive.file_tofix.entity.FileEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий для файлов
 */
public interface FileRepository extends CrudRepository<FileEntity, Long> {

    FileEntity findByFileName(String filename);
}