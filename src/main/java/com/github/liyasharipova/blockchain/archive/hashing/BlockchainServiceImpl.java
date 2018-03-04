package com.github.liyasharipova.blockchain.archive.hashing;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class BlockchainServiceImpl implements BlockchainService {

    private List<Block> blockchain = new ArrayList<>();

    @Override
    public List<Block> getBlockChain() {
        return blockchain;
    }

    @Override
    public void addBlock(Block block) {

    }

    @Override
    public String getLastBlockHash() {
        if (blockchain.isEmpty()) {
            return EMPTY_HASH;
        }
        return blockchain.get(blockchain.size() - 1).getHash();
    }
}