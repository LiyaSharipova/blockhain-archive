package com.github.liyasharipova.blockchain.archive.hashing.block;

import com.github.liyasharipova.blockchain.archive.hashing.blockchain.BlockchainService;
import com.github.liyasharipova.blockchain.archive.hashing.transaction.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Сервис для работы с блоком {@link BlockDto}
 */
@Service
public class BlockServiceImpl implements BlockService {

    /** Максимальное количество транзакций в блокчейне */
    private static final int MAXIMUM_TRANSACTIONS_PER_BLOCK = 42;

    /** Максимальное время паузы между добавлением транзакций в блок */
    private static final int MAXIMUM_TIMEOUT_OF_LAST_TRANSACTION = 10;

    /**
     * Если блокчейн пустой в самом начале работы приложения
     */
    private BlockDto currentBlock = new BlockDto(BlockchainService.EMPTY_PREVIOUS_HASH);

    /** Сервис для работы с блокчейном */
    @Autowired
    private BlockchainService blockchainService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTransaction(TransactionDto transaction) {

        if (!currentBlock.getTransactions().isEmpty()) {
            addToBlockchainIfNeeded();
        }

        currentBlock.addTransaction(transaction);
    }

    /**
     * Проверяем, нужно ли закрыть блок в случае либо его переполнения (MAXIMUM_TRANSACTIONS_PER_BLOCK),
     * либо в случае большой временной паузы (MAXIMUM_TIMEOUT_OF_LAST_TRANSACTION).
     * Если нужно закрыть блок, то сначала добавляем его в блокчейн, а потом создаем заново
     * текущий блок для дальнейшего добавления транзакций в уже новый текущий блок.
     */
    private void addToBlockchainIfNeeded() {
        ArrayList<TransactionDto> currentTransactions = currentBlock.getTransactions();
        int currentTransactionsSize = currentTransactions.size();
        long currentTime = new Date().getTime();
        long lastTransactionTime = currentTransactions.get(currentTransactionsSize - 1).getUploadDateTime();
        long tenMinutesInSec = TimeUnit.MINUTES
                .toSeconds(MAXIMUM_TIMEOUT_OF_LAST_TRANSACTION);
        if (currentTransactionsSize >= MAXIMUM_TRANSACTIONS_PER_BLOCK
                || (currentTime - lastTransactionTime) > tenMinutesInSec) {

            blockchainService.addBlock(currentBlock);
            currentBlock = new BlockDto(blockchainService.getLastBlockHash());
        }
    }
}