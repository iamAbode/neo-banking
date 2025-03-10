package com.neo.identity.util;

import com.neo.identity.entity.User;
import com.neo.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author ABODE
 * @Date 2025/03/10 12:01 AM
 */
@Component
@RequiredArgsConstructor
public class UserDetailUtil {

    @Autowired
    private final UserRepository repository;

    public User getUserDetail(String username){
       return repository.findByUsername(username).orElseThrow(null);
    }

}
