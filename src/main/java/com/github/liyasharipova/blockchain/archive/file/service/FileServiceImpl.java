package com.github.liyasharipova.blockchain.archive.file.service;

import com.github.liyasharipova.blockchain.archive.file.entity.FileEntity;
import com.github.liyasharipova.blockchain.archive.file.exception.StorageException;
import com.github.liyasharipova.blockchain.archive.file.exception.StorageFileNotFoundException;
import com.github.liyasharipova.blockchain.archive.file.repostiory.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(
            FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void store(MultipartFile file) {
        String name = file.getOriginalFilename();

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + name);
            }

            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(name);
            fileEntity.setData(file.getBytes());

            fileRepository.save(fileEntity);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + name, e);
        }
    }

    @Override
    public List<Resource> loadAll() {
        List<Resource> files = new ArrayList<>();

        fileRepository.findAll().forEach(fileEntity -> {
            ByteArrayResource resource = new ByteArrayResource(fileEntity.getData());
            files.add(resource);
        });

        return files;

    }

    @Override
    public byte[] load(String filename) {
        return fileRepository.findByFilName(filename).getData();
    }

    @Override
    public Resource loadAsResource(String filename) {
        byte[] fileData = load(filename);
        Resource resource = new ByteArrayResource(fileData);
        if (resource.exists()) {
            return resource;
        } else {
            throw new StorageFileNotFoundException(
                    "Could not read data from file: " + filename);

        }
    }

    @Override
    public void deleteAll() {
        fileRepository.deleteAll();
    }

}
