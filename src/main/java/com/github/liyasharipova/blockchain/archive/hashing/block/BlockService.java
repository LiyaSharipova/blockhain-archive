package com.github.liyasharipova.blockchain.archive.hashing.block;

import com.github.liyasharipova.blockchain.archive.hashing.transaction.TransactionDto;

/**
 * Сервис для работы с блоком
 */
public interface BlockService {

    /**
     *  Добавление транзакции  в блок
     */
    void addTransaction(TransactionDto transaction);
}
