package com.neo.gateway.config;

import com.neo.common.ApiConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ABODE
 * @Date 2025/03/10 9:03 PM
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "api.services")
public class ApiServicePath {

    private ApiConfig customer = new ApiConfig();
    private ApiConfig account = new ApiConfig();
    private ApiConfig transaction = new ApiConfig();
    private ApiConfig identity = new ApiConfig();
}
