package com.github.liyasharipova.blockchain.archive.hashing.blockchain;

import com.github.liyasharipova.blockchain.archive.hashing.block.BlockDto;

import java.util.List;

/**
 * Сервис для работы с блокчейн
 */
public interface BlockchainService {

    /** Хэш  предыдущего блока {@link BlockDto#previousHash} для первого блока в блокчейне */
    String EMPTY_PREVIOUS_HASH = "0";

    /** Получить связку блокчейн */
    List<BlockDto> getBlockChain();

    /** Добавить блок в блокчейн */
    void addBlock(BlockDto block);

    /** Получить хэш последнего блока*/
    String getLastBlockHash();
}
