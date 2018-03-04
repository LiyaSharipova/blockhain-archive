package com.github.liyasharipova.blockchain.archive.hashing.blockchain;

import com.github.liyasharipova.blockchain.archive.blockchain_old.NoobChain;
import com.github.liyasharipova.blockchain.archive.hashing.block.BlockDto;
import com.github.liyasharipova.blockchain.archive.hashing.exception.ChainValidityException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class BlockchainServiceImpl implements BlockchainService {

    private final int DIFFICULTY = 3;

    private List<BlockDto> blockchain = new ArrayList<>();

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
                throw new ChainValidityException("#Current Hashes not equal");
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.getHash().equals(currentBlock.getHash())) {
                throw new ChainValidityException("#Previous Hashes not equal");
            }
            //check if hash is solved
            if (!currentBlock.getHash().substring(0, NoobChain.difficulty).equals(hashTarget)) {
                throw new ChainValidityException("#This block hasn't been mined");
            }

        }
        System.out.println("Blockchain is valid");

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