package com.neo.identity.repository;

import com.neo.identity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author ABODE
 * @Date 2025/03/09 6:58 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>  findByUsername(String username);
}
