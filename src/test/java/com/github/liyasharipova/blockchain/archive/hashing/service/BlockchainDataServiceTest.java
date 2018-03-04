package com.github.liyasharipova.blockchain.archive.hashing.service;

import com.github.liyasharipova.blockchain.archive.AppConfig;
import com.github.liyasharipova.blockchain.archive.hashing.blockchaindata.BlockchainDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class BlockchainDataServiceTest {

    @Autowired
    private BlockchainDataService blockchainDataService;

    @Test
    public void placeToBlockchain() {
        byte[] fileData = "test-data".getBytes();
        String userId = "test-user-id";
        long currentTimeInSec = new Date().getTime();
        blockchainDataService.placeToBlockchain(fileData, userId, currentTimeInSec);
    }
}
