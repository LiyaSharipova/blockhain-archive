package com.github.liyasharipova.blockchain.archive.hashing;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Block {

    /**
     * Хэш блока, вычисляюющий на основе хэша пред. блока, merkleRoot (хэш на основе всех транзакций),
     * timeStamp, nonce
     */
    private String hash;

    /** Хэш предыдущего блока */
    private String previousHash;

    /** Хэш, высчитываемый в цикле с помощью конкатенации всех соседних транзакций */
    private String merkleRoot;

    /** Список транзакций в блоке */
    private ArrayList<Transaction> transactions = new ArrayList<>();

    /** Время создания блока */
    private long timeStamp;

    /** сгенерированное случайно число обеспечивающее сложность хеша */
    private int nonce;

    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash();
    }

    /** Вычисляем новый хэш на основе содержимого блока */
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        merkleRoot
        );
        return calculatedhash;
    }

    /**
     * Увеличиваем значение nonce пока нужный хэш не будет найден
     *
     * @param difficulty сколько нулей будет в строке target
     */
    public void mineBlock(int difficulty) {
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    /**
     * Добавляем транзакцию к блоку
     */
    public boolean addTransaction(Transaction transaction) {
        //если блок не пустой, проверяем валидна ли транзакция
        if (transaction == null) {
            return false;
        }

        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }

}
