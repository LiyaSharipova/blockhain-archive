package com.github.liyasharipova.blockchain.archive.hashing.blockchain;

import com.github.liyasharipova.blockchain.archive.blockchain_old.NoobChain;
import com.github.liyasharipova.blockchain.archive.hashing.block.BlockDto;
import com.github.liyasharipova.blockchain.archive.hashing.block.BlockEntity;
import com.github.liyasharipova.blockchain.archive.hashing.block.BlockRepository;
import com.github.liyasharipova.blockchain.archive.hashing.exception.ChainValidityException;
import com.github.liyasharipova.blockchain.archive.hashing.transaction.TransactionEntity;
import com.github.liyasharipova.blockchain.archive.hashing.transaction.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * {@inheritDoc}
 */
@Service
//@Transactional
public class BlockchainServiceImpl implements BlockchainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockchainServiceImpl.class);

    @Value("${difficulty}")
    private int DIFFICULTY;

    private List<BlockDto> blockchain = new ArrayList<>();

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<BlockDto> getBlockChain() {
        return blockchain;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBlock(BlockDto block) {
        checkChainValidity();
        block.mineBlock(DIFFICULTY);
        blockchain.add(block);

        BlockEntity blockEntity = new BlockEntity();
        blockEntity.setHash(block.getHash());
        blockEntity.setPreviousHash(block.getPreviousHash());
        blockRepository.save(blockEntity);

        block.getTransactions().forEach(transactionDto -> {
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setFileHash(transactionDto.getHash());
            transactionEntity.setUserId(transactionDto.getUserId());
            transactionEntity.setCreateTime(transactionDto.getUploadDateTime());
            transactionEntity.setBlockHash(blockEntity.getHash());
            transactionEntity.setFileData(transactionDto.getData());
            transactionRepository.save(transactionEntity);
        });

        LOGGER.info("{} saved with hash: {}",
                    BlockEntity.class.getSimpleName(),
                    blockEntity.getHash().substring(0, 6));
    }

    private void checkChainValidity() {
        BlockDto currentBlock;
        BlockDto previousBlock;
        String hashTarget = new String(new char[NoobChain.difficulty]).replace('\0', '0');
        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {

            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                throw new ChainValidityException("Current Hashes not equal");
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.getHash().equals(currentBlock.getHash())) {
                throw new ChainValidityException("Previous Hashes not equal");
            }
            //check if hash is solved
            if (!currentBlock.getHash().substring(0, NoobChain.difficulty).equals(hashTarget)) {
                throw new ChainValidityException("This block hasn't been mined");
            }

        }
        LOGGER.info("Blockchain with size {} is valid", blockchain.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastBlockHash() {
        if (blockchain.isEmpty()) {
            return EMPTY_PREVIOUS_HASH;
        }
        return blockchain.get(blockchain.size() - 1).getHash();
    }
}