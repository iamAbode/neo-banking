package com.neo.account.client;

import com.neo.common.dto.CustomerDTO;
import com.neo.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * @Author ABODE
 * @Date 2025/03/08 5:11 PM
 */
@RequiredArgsConstructor
@Component
public class CustomerServiceClient {

    @Value("${inventory.service.url:http://localhost:8081}")
    private String customerServiceUrl;

    private final RestClient restClient;

    public BaseResponse<CustomerDTO> getCustomer(String customerId) {
        return restClient.mutate()
                .baseUrl(customerServiceUrl)
                .build()
                .get()
                .uri("/api/customer?customerId=" + customerId)
                .retrieve()
                .body(new ParameterizedTypeReference<BaseResponse<CustomerDTO>>() {});
    }
}
