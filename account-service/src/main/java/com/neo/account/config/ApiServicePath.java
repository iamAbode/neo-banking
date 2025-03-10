package com.neo.account.config;

import com.neo.common.ApiConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ABODE
 * @Date 2025/03/10 8:15 PM
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "account.api.services")
public class ApiServicePath {

    private ApiConfig customer = new ApiConfig();
    private ApiConfig transaction = new ApiConfig();
}
