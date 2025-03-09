package com.neo.account.repository;

import com.neo.account.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @Author ABODE
 * @Date 2025/03/08 1:28 PM
 */
@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {

    List<CustomerAccount> findByCustomerId(String customerId);

    @Modifying
    @Transactional
    @Query("UPDATE CustomerAccount c SET c.balance=?2 WHERE c.id=?1")
    void updateBalance(Long id, BigDecimal balance);

}
