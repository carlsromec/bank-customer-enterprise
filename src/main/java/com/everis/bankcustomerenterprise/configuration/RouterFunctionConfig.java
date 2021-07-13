package com.everis.bankcustomerenterprise.configuration;

import com.everis.bankcustomerenterprise.handler.CustomerEnterpriseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> routers(CustomerEnterpriseHandler handler){
        return route(GET("/api/v2/customerenterprise"),handler::listCustomerEnterprise)
                .andRoute(GET("/api/v2/customerenterprise/{id}"),handler::listCustomerEnterpriseId)
                .andRoute(POST("/api/v2/customerenterprise"), handler::addCustomerEnterprise)
                .andRoute(PUT("/api/v2/customerenterprise/{id}"), handler::editCustomerEnterprise);
    }

}
