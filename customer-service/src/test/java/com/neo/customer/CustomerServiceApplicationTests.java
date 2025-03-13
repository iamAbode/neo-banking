package com.neo.customer;

import com.neo.customer.config.ApiServicePath;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@EnableConfigurationProperties(ApiServicePath.class)
class CustomerServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
