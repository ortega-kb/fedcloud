package com.ortega.api_gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Value("${api-gateway.task-manager}")
    private String taskManager;

    @Value("${api-gateway.resource-manager}")
    private String resourceManager;

    @Bean
    public RouteLocator apiGatewayRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        "task-manager",
                        r -> r.path("/api/v1/task-manager/**")
                        .uri(taskManager)
                )
                .route("resource-manager",
                        r -> r.path("/api/v1/resource-manager/**")
                        .uri(resourceManager)
                )
                .build();
    }
}
