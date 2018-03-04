package com.github.liyasharipova.blockchain.archive.hashing.block;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<BlockEntity, String> {
}
