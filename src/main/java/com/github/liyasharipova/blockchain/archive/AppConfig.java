package com.github.liyasharipova.blockchain.archive;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("app")
@Configuration
@ComponentScan(basePackages = {"com.github.liyasharipova"})
public class AppConfig {

}