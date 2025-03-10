package com.neo.account.client;

import com.neo.account.config.ApiServicePath;
import com.neo.account.dto.TransactionRequest;
import com.neo.common.dto.TransactionDTO;
import com.neo.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * @Author ABODE
 * @Date 2025/03/08 11:38 PM
 */

@RequiredArgsConstructor
@Component
public class TransactionServiceClient {

    private final ApiServicePath apiServicePath;

    private final RestClient restClient;

    public BaseResponse<TransactionDTO> processTransaction(TransactionRequest request) {
        return restClient.mutate()
                .baseUrl(apiServicePath.getTransaction().getBaseUrl())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .post()
                .uri(apiServicePath.getTransaction().getRelativePath())
                .body(request) // Pass the request body
                .retrieve()
                .body(new ParameterizedTypeReference<BaseResponse<TransactionDTO>>() {});
    }
}
