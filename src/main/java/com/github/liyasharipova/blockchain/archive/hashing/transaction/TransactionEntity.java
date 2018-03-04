package com.github.liyasharipova.blockchain.archive.hashing.transaction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

@Entity
@Table(name = "transaction", schema = "blockchain_archive", catalog = "postgres")
public class TransactionEntity {
    private Long id;
    private String fileHash;
    private String blockHash;
    private String userId;
    private Timestamp trTime;
    private String fileName;
    private byte[] fileData;

    @Id
    @Column(name = "id")
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
    @Column(name = "tr_time")
    public Timestamp getTrTime() {
        return trTime;
    }

    public void setTrTime(Timestamp trTime) {
        this.trTime = trTime;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionEntity that = (TransactionEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (fileHash != null ? !fileHash.equals(that.fileHash) : that.fileHash != null) return false;
        if (blockHash != null ? !blockHash.equals(that.blockHash) : that.blockHash != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (trTime != null ? !trTime.equals(that.trTime) : that.trTime != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (!Arrays.equals(fileData, that.fileData)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fileHash != null ? fileHash.hashCode() : 0);
        result = 31 * result + (blockHash != null ? blockHash.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (trTime != null ? trTime.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(fileData);
        return result;
    }
}
