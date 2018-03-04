package com.github.liyasharipova.blockchain.archive.hashing.blockchain;

import com.github.liyasharipova.blockchain.archive.hashing.block.Block;

import java.util.List;

/**
 * Сервис для работы с блокчейн
 */
public interface BlockchainService {

    /** Хэш  предыдущего блока {@link Block#previousHash} для первого блока в блокчейне */
    String EMPTY_PREVIOUS_HASH = "0";

    /** Получить связку блокчейн */
    List<Block> getBlockChain();

    /** Добавить блок в блокчейн */
    void addBlock(Block block);

    /** Получить хэш последнего блока*/
    String getLastBlockHash();
}
