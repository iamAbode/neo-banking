package com.neo.customer.repository;

import com.neo.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author ABODE
 * @Date 2025/03/07 11:12 PM
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
