package com.github.liyasharipova.blockchain.archive;

import com.github.liyasharipova.blockchain.archive.file.properties.StorageProperties;
import com.github.liyasharipova.blockchain.archive.file.service.FileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(FileService fileService) {
        return (args) -> {
            fileService.deleteAll();
        };
    }
}
