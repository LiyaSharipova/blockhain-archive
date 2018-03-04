package com.github.liyasharipova.blockchain.archive.hashing.block;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends CrudRepository<BlockEntity, String>{
}
