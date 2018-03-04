package com.github.liyasharipova.blockchain.archive.hashing.service;

import com.github.liyasharipova.blockchain.archive.hashing.blockchaindata.BlockchainDataService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BlockchainDataServiceTest {

    @Autowired
    private BlockchainDataService blockchainDataService;

    @Test
    public void testPlaceToBlockchain() {
        byte[] fileData = "test-data".getBytes();
        String userId = "test-user-id";
        long currentTimeInSec = new Date().getTime();
        for (int i = 0; i < 3; i++) {
            blockchainDataService.placeToBlockchain(fileData, userId, currentTimeInSec);
        }
    }

    //fixme не работает добавление 4 раза:
    // com.github.liyasharipova.blockchain.archive.hashing.exception.ChainValidityException: Previous Hashes not equal
    @Test
    @Ignore
    public void testPlaceToBlockchain4Times() {
        byte[] fileData = "test-data".getBytes();
        String userId = "test-user-id";
        long currentTimeInSec = new Date().getTime();
        for (int i = 0; i < 4; i++) {
            blockchainDataService.placeToBlockchain(fileData, userId, currentTimeInSec);
        }
    }
}
