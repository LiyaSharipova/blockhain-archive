package com.github.liyasharipova.blockchain.archive.hashing.service;

import com.github.liyasharipova.blockchain.archive.hashing.block.BlockService;
import com.github.liyasharipova.blockchain.archive.hashing.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class BlockchainDataServiceImpl implements BlockchainDataService {

    @Autowired
    private BlockService blockService;

    @Override
    public void placeToBlockchain(byte[] fileData, String userId, long uploadDateTime) {

        Transaction transaction = new Transaction(fileData, userId, uploadDateTime);

        blockService.addTransaction(transaction);

        
    }
}