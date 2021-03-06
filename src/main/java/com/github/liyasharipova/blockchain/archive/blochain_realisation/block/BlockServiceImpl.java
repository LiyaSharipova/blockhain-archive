package com.github.liyasharipova.blockchain.archive.blochain_realisation.block;

import com.github.liyasharipova.blockchain.archive.blochain_realisation.blockchain.BlockchainService;
import com.github.liyasharipova.blockchain.archive.blochain_realisation.transaction.TransactionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Сервис для работы с блоком {@link BlockDto}
 */
@Service
public class BlockServiceImpl implements BlockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockDto.class);

    /** Максимальное количество транзакций в блокчейне */
    @Value("${maximum.transactions.per.block}")
    private static int MAXIMUM_TRANSACTIONS_PER_BLOCK;

    /** Максимальное время паузы между добавлением транзакций в блок */
    @Value("${maximum.timeout.of.last.transaction.sec}")
    private static int MAXIMUM_TIMEOUT_OF_LAST_TRANSACTION_SEC;

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
        LOGGER.info("Added new transaction in current block with hash {} ",
                    transaction.getHash().substring(0, 6));
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
        long tenMinutesInSec = TimeUnit.SECONDS
                .toMillis(MAXIMUM_TIMEOUT_OF_LAST_TRANSACTION_SEC);
        if (currentTransactionsSize >= MAXIMUM_TRANSACTIONS_PER_BLOCK
                || (currentTime - lastTransactionTime) > tenMinutesInSec) {

            blockchainService.addBlock(currentBlock);
            String lastBlockHash = blockchainService.getLastBlockHash();
            currentBlock = new BlockDto(lastBlockHash);
            LOGGER.info("Current block re-initialized for new transactions with previous hash {}",
                        lastBlockHash.substring(0, 6));
        }
    }
}