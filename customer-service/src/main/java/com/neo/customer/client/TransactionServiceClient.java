package com.neo.customer.client;

import com.neo.common.dto.CustomerDTO;
import com.neo.common.dto.TransactionDTO;
import com.neo.common.response.BaseResponse;
import com.neo.customer.config.ApiServicePath;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * @Author ABODE
 * @Date 2025/03/09 10:01 AM
 */
@Component
@RequiredArgsConstructor
public class TransactionServiceClient {

    private final ApiServicePath apiServicePath;

    private final RestClient restClient;

    public BaseResponse<List<TransactionDTO>> getCustomerTransactions(String customerId) {
        return restClient.mutate()
                .baseUrl(apiServicePath.getTransaction().getBaseUrl())
                .build()
                .get()
                .uri(apiServicePath.getTransaction().getRelativePath() + customerId)
                .retrieve()
                .body(new ParameterizedTypeReference<BaseResponse<List<TransactionDTO>>>() {});
    }
}
