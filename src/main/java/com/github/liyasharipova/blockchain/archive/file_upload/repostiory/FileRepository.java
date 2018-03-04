package com.github.liyasharipova.blockchain.archive.file_upload.repostiory;

import com.github.liyasharipova.blockchain.archive.file_upload.entity.FileEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий для файлов
 */
public interface FileRepository extends CrudRepository<FileEntity, Long> {

    FileEntity findByFileName(String filename);
}