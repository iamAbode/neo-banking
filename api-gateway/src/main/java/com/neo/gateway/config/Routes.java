package com.neo.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

/**
 * @Author ABODE
 * @Date 2025/03/09 9:15 PM
 */
@Configuration
@RequiredArgsConstructor
public class Routes {

    private final ApiServicePath apiServicePath;

    @Bean
    public RouterFunction<ServerResponse> customerServiceRoute() {
        return route("customer_service")
                .route(RequestPredicates.path("/api/customer/**"), HandlerFunctions.http(apiServicePath.getCustomer().getBaseUrl()))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("customerServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> accountServiceRoute() {
        return route("account_service")
                .route(RequestPredicates.path("/api/account/**"), HandlerFunctions.http(apiServicePath.getAccount().getBaseUrl()))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("accountServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> transactionServiceRoute() {
        return route("transaction_service")
                .route(RequestPredicates.path("/api/transaction/**"), HandlerFunctions.http(apiServicePath.getTransaction().getBaseUrl()))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("transactionServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> identityServiceRoute() {
        return route("identity_service")
                .route(RequestPredicates.path("/api/identity/**"), HandlerFunctions.http(apiServicePath.getIdentity().getBaseUrl()))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("identityServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

        @Bean
    public RouterFunction<ServerResponse> customerServiceSwaggerRoute() {
        return route("customer_service_swagger")
                .route(RequestPredicates.path("/aggregate/customer-service/v1/api-docs"), HandlerFunctions.http(apiServicePath.getCustomer().getBaseUrl()))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("customerServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

        @Bean
    public RouterFunction<ServerResponse> accountServiceSwaggerRoute() {
        return route("account_service_swagger")
                .route(RequestPredicates.path("/aggregate/account-service/v1/api-docs"), HandlerFunctions.http(apiServicePath.getAccount().getBaseUrl()))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("accountServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> transactionServiceSwaggerRoute() {
        return route("transaction_service_swagger")
                .route(RequestPredicates.path("/aggregate/transaction-service/v1/api-docs"), HandlerFunctions.http(apiServicePath.getTransaction().getBaseUrl()))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("transactionServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallbackRoute")
                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service Unavailable, please try again later"))
                .build();
    }
}
