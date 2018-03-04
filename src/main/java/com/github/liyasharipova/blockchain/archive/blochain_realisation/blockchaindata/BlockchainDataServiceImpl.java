package com.github.liyasharipova.blockchain.archive.blochain_realisation.blockchaindata;

import com.github.liyasharipova.blockchain.archive.blochain_realisation.block.BlockService;
import com.github.liyasharipova.blockchain.archive.blochain_realisation.transaction.TransactionDto;
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
    public boolean placeToBlockchain(byte[] fileData, String userId, long uploadDateTime) {

        TransactionDto transaction = new TransactionDto(fileData, userId, uploadDateTime);

        blockService.addTransaction(transaction);

        return false;
    }
}