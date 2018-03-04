package com.github.liyasharipova.blockchain.archive.hashing.block;

import com.github.liyasharipova.blockchain.archive.hashing.transaction.Transaction;

/**
 * Сервис для работы с блоком
 */
public interface BlockService {

    /**
     *  Добавление транзакции  в блок
     */
    void addTransaction(Transaction transaction);
}
