package com.neo.transaction.repository;

import com.neo.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author ABODE
 * @Date 2025/03/08 9:53 PM
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
