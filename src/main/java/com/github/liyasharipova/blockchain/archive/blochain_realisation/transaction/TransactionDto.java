package com.github.liyasharipova.blockchain.archive.blochain_realisation.transaction;

import com.github.liyasharipova.blockchain.archive.blochain_realisation.util.StringUtil;
import lombok.Data;

import java.util.Arrays;

@Data
public class TransactionDto {

    /**
     * Хэш транзакции
     */
    private String hash;
    /**
     * Содержимое файла
     */
    private byte[] data;
    /**
     * Идентификатор пользователя
     */
    private final String userId;
    /**
     * Время загрузки файла
     */
    private long uploadDateTime;
    /**
     * Счетчик количества транзакций для предотвращения коллизий
     */
    private static int sequence = 0;

    public TransactionDto(byte[] data, String userId, long uploadDateTime) {
        this.data = data;
        this.userId = userId;
        this.uploadDateTime = uploadDateTime;
        this.hash = calculateHash();
    }

    private String calculateHash() {
        sequence++;
        return StringUtil.applyStribog(Arrays.toString(data) + userId + uploadDateTime + sequence);
    }
}
