package com.neo.customer.client;

import com.neo.common.dto.CustomerAccountDTO;
import com.neo.common.dto.CustomerDTO;
import com.neo.common.response.BaseResponse;
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
public class AccountServiceClient {

    @Value("${account.service.url:http://localhost:8082}")
    private String accountServiceUrl;

    private final RestClient restClient;

    public BaseResponse<List<CustomerAccountDTO>> getCustomerAccounts(String customerId) {
        return restClient.mutate()
                .baseUrl(accountServiceUrl)
                .build()
                .get()
                .uri("/api/account?customerId=" + customerId)
                .retrieve()
                .body(new ParameterizedTypeReference<BaseResponse<List<CustomerAccountDTO>>>() {});
    }
}
