package com.github.liyasharipova.blockchain.archive.hashing;

/**
 * Сервис для работы с блоком
 */
public interface BlockService {

    /**
     *  Добавление транзакции  в блок
     */
    void addTransaction(Transaction transaction);
}
