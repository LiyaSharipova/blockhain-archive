package com.github.liyasharipova.blockchain.archive.blochain_realisation.block;

import com.github.liyasharipova.blockchain.archive.blochain_realisation.transaction.TransactionDto;

/**
 * Сервис для работы с блоком
 */
public interface BlockService {

    /**
     *  Добавление транзакции  в блок
     */
    void addTransaction(TransactionDto transaction);
}
