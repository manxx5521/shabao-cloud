package com.xiaoshabao.cloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class GatewayCofiguration {

  @Bean
  @Order
  public RouteLocator customizedRoutes(RouteLocatorBuilder builder) {
    return builder.routes().route(r -> r.path("/blog/**").filters(f -> f.stripPrefix(1)).uri("lb://SHABAO-BLOG"))
      .route(r -> r.path("/oss/**").filters(f -> f.stripPrefix(1)).uri("lb://SHABAO-OSS-CLOUD")).build();
  }

}
