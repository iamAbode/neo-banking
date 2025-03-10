package com.neo.gateway.service;

import com.neo.gateway.client.IdentityServiceClient;
import com.neo.gateway.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.neo.gateway.model.CustomUserDetails;

import java.util.Optional;

/**
 * @Author ABODE
 * @Date 2025/02/17 8:13 PM
 */
@Service
public class UserInfoService implements UserDetailsService {

    private final IdentityServiceClient identityServiceClient;

    public UserInfoService(IdentityServiceClient identityServiceClient) {
        this.identityServiceClient = identityServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDTO> userDetail = getUserDetail(username); // Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return userDetail.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    private Optional<UserDTO> getUserDetail(String username) {
        return Optional.ofNullable(identityServiceClient.getUser(username));

    }

}
