package com.github.liyasharipova.blockchain.archive.hashing.transaction;


import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

}
