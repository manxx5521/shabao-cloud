package com.xiaoshabao.cloud.gateway.config;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.xiaoshabao.cloud.gateway.dao.ClientDao;
import com.xiaoshabao.cloud.gateway.dao.ClientEntity;

@Configuration
public class GatewayCofiguration {

  @Autowired
  private ClientDao clientDao;

  @Bean
  @Order
  public RouteLocator customizedRoutes(RouteLocatorBuilder builder) {
    Builder routes = builder.routes().route(r -> r.path("/login").uri("lb://SHABAO-AUTH"));
    List<ClientEntity> list = clientDao.getClientList();
    for (ClientEntity client : list) {
      routes.route(r -> r.path(client.getFilterPath()).filters(f -> f.stripPrefix(1))
        .uri("lb://" + client.getClientId().toUpperCase(Locale.US)));
    }
    return routes.build();
  }

}
