package com.neo.gateway.client;

import com.neo.gateway.config.ApiServicePath;
import com.neo.gateway.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * @Author ABODE
 * @Date 2025/03/08 5:11 PM
 */
//@RequiredArgsConstructor
@Component
@RequiredArgsConstructor
public class IdentityServiceClient {

    private final ApiServicePath apiServicePath;
    private final RestClient restClient;

    public UserDTO getUser(String username) {
        return restClient.mutate()
                .baseUrl(apiServicePath.getIdentity().getBaseUrl())
                .build()
                .get()
                .uri(apiServicePath.getIdentity().getRelativePath() + username)
                .retrieve()
                .body(UserDTO.class);
    }
}
