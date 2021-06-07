package com.xiaoshabao.auth.service;

import java.security.KeyPair;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;

public class ClientDetailsService extends JdbcClientDetailsService {
  
  private final DataSource dataSource;

  @Autowired
  public ClientDetailsService(DataSource dataSource) {
    super(dataSource);
    this.dataSource = dataSource;
   
  }

  /**
   * 重写原生方法支持redis缓存
   * @param clientId
   * @return
   */
  @Override
  //  @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
  public ClientDetails loadClientByClientId(String clientId) {
    return super.loadClientByClientId(clientId);
  }

}
