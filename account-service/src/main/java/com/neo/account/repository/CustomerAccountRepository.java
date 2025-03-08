package com.neo.account.repository;

import com.neo.account.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author ABODE
 * @Date 2025/03/08 1:28 PM
 */
@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {

    Optional<CustomerAccount> findByCustomerId(String customerId);
}
