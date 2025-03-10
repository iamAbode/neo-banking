package com.neo.customer.config;

import com.neo.common.ApiConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ABODE
 * @Date 2025/03/10 5:48 PM
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "api.services")
public class ApiServicePath {

    private ApiConfig account = new ApiConfig();
    private ApiConfig transaction = new ApiConfig();

}
