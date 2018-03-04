package com.github.liyasharipova.blockchain.archive.hashing;

import com.github.liyasharipova.blockchain.archive.blockchain.StringUtil;
import lombok.Data;

import java.util.Arrays;

@Data
public class Transaction {

    private String hash; //Contains a hash of transaction*

    private byte[] data; //Contains a hash of transaction*

    private final String userId;

    private long uploadDateTime;

    private static int sequence = 0; //A rough count of how many transactions have been generated

    public Transaction(byte[] data, String userId, long uploadDateTime) {
        this.data = data;
        this.userId = userId;
        this.uploadDateTime = uploadDateTime;
        this.hash = calculateHash();
    }

    private String calculateHash() {
        sequence++; //increase the sequence to avoid 2 identical transactions having the same hash
        return StringUtil.applySha256(Arrays.toString(data) + userId + uploadDateTime + sequence);
    }
}
