package com.github.liyasharipova.blockchain.archive.hashing;

import java.util.List;

/**
 *
 */
public interface BlockchainService {

    String EMPTY_HASH = "0";

    List<Block> getBlockChain();

    void addBlock(Block block);

    String getLastBlockHash();
}
