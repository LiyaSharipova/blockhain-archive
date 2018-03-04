package com.github.liyasharipova.blockchain.archive.blochain_realisation.transaction;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "transaction", schema = "blockchain_archive", catalog = "postgres")
public class TransactionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fileHash;
    private String blockHash;
    private String userId;
    private long createTime;
    private String fileName;
    private byte[] fileData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "file_hash")
    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    @Basic
    @Column(name = "block_hash")
    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "create_time")
    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_data")
    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionEntity that = (TransactionEntity) o;
        return createTime == that.createTime &&
                Objects.equals(id, that.id) &&
                Objects.equals(fileHash, that.fileHash) &&
                Objects.equals(blockHash, that.blockHash) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(fileName, that.fileName) &&
                Arrays.equals(fileData, that.fileData);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, fileHash, blockHash, userId, createTime, fileName);
        result = 31 * result + Arrays.hashCode(fileData);
        return result;
    }
}
