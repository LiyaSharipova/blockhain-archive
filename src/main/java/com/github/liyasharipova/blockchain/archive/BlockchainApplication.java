package com.github.liyasharipova.blockchain.archive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import ru.ubmb.jstribog.StribogProvider;

import java.security.Security;

@SpringBootApplication
@ComponentScan("com.github.liyasharipova")
public class BlockchainApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainApplication.class, args);
        if (Security.getProvider("JStribog") == null) {
            Security.addProvider(new StribogProvider());
        }

    }
}
