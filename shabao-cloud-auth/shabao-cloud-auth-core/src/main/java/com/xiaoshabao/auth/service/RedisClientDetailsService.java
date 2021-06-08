package com.xiaoshabao.auth.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;

@Component
public class RedisClientDetailsService extends JdbcClientDetailsService implements InitializingBean {


  @Autowired
  public RedisClientDetailsService(DataSource dataSource) {
    super(dataSource);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    String column = "resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
      + "refresh_token_validity, additional_information, autoapprove";
    setSelectClientDetailsSql(
      "select client_id,client_secret, " + column + " from sys_cloud_client_details where client_id = ?");
    setFindClientDetailsSql("select client_id, " + column + " from sys_cloud_client_details order by client_id");
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
