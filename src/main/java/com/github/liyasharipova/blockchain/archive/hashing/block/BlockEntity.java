package com.github.liyasharipova.blockchain.archive.hashing.block;

import javax.persistence.*;

@Entity
@Table(name = "block", schema = "blockchain_archive", catalog = "postgres")
public class BlockEntity {
    private String hash;
    private String previousHash;

    @Id
    @Column(name = "hash")
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Basic
    @Column(name = "previous_hash")
    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockEntity that = (BlockEntity) o;

        if (hash != null ? !hash.equals(that.hash) : that.hash != null) return false;
        if (previousHash != null ? !previousHash.equals(that.previousHash) : that.previousHash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hash != null ? hash.hashCode() : 0;
        result = 31 * result + (previousHash != null ? previousHash.hashCode() : 0);
        return result;
    }
}
