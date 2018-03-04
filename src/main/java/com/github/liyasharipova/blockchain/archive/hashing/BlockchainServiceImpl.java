package com.github.liyasharipova.blockchain.archive.hashing;

import com.github.liyasharipova.blockchain.archive.blockchain.Transaction;
import com.github.liyasharipova.blockchain.archive.blockchain.TransactionOutput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class BlockchainServiceImpl implements BlockchainService {

    private List<Block> blockchain = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Block> getBlockChain() {
        return blockchain;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBlock(Block block) {
        checkChainValidity();
        blockchain.add(block);
    }

    private void checkChainValidity() {
        com.github.liyasharipova.blockchain.archive.blockchain.Block currentBlock;
        com.github.liyasharipova.blockchain.archive.blockchain.Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        HashMap<String,TransactionOutput> tempUTXOs = new HashMap<String,TransactionOutput>(); //a temporary working list of unspent transactions at a given block state.
        //		tempUTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {

            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("#Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("#Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("#This block hasn't been mined");
                return false;
            }

            //loop thru blockchains transactions:
            TransactionOutput tempOutput;
            for(int t=0; t <currentBlock.transactions.size(); t++) {
                Transaction currentTransaction = currentBlock.transactions.get(t);

                if(!currentTransaction.verifiySignature()) {
                    System.out.println("#Signature on Transaction(" + t + ") is Invalid");
                    return false;
                }
                //				if(currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()) {
                //					System.out.println("#Inputs are note equal to outputs on Transaction(" + t + ")");
                //					return false;
                //				}

                //				for(TransactionInput input: currentTransaction.inputs) {
                //					tempOutput = tempUTXOs.get(input.transactionOutputId);
                //
                //					if(tempOutput == null) {
                //						System.out.println("#Referenced input on Transaction(" + t + ") is Missing");
                //						return false;
                //					}
                //
                //					if(input.UTXO.value != tempOutput.value) {
                //						System.out.println("#Referenced input Transaction(" + t + ") value is Invalid");
                //						return false;
                //					}
                //
                //					tempUTXOs.remove(input.transactionOutputId);
                //				}

                //				for(TransactionOutput output: currentTransaction.outputs) {
                //					tempUTXOs.put(output.id, output);
                //				}
                //
                //				if( currentTransaction.outputs.get(0).reciepient != currentTransaction.reciepient) {
                //					System.out.println("#Transaction(" + t + ") output reciepient is not who it should be");
                //					return false;
                //				}
                //				if( currentTransaction.outputs.get(1).reciepient != currentTransaction.sender) {
                //					System.out.println("#Transaction(" + t + ") output 'change' is not sender.");
                //					return false;
                //				}

            }

        }
        System.out.println("Blockchain is valid");
        return true;

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