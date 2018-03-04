package com.github.liyasharipova.blockchain.archive.hashing;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class BlockchainServiceImpl implements BlockchainService {

    private List<Block> blockchain = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Block> getBlockChain() {
        return blockchain;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBlock(Block block) {
        blockchain.add(block);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastBlockHash() {
        if (blockchain.isEmpty()) {
            return EMPTY_PREVIOUS_HASH;
        }
        return blockchain.get(blockchain.size() - 1).getHash();
    }
}