package com.xiaoshabao.admin.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;

@EnableWebSecurity
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {
  
  @Value("${app.domain}")
  private String domain;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
      .authorizeRequests((authorizeRequests) -> authorizeRequests.mvcMatchers("/login","/callback").permitAll()
        .anyRequest().authenticated())
      .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
      // 登录页面url
    .formLogin().loginPage("/login");
    // @formatter:on
  }
  
  /**
   * 自定义令牌解析
   * @return
   */
  @Bean
  public BearerTokenResolver bearerTokenResolver() {
      DefaultBearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();
      //get请求解析超链接参数
      bearerTokenResolver.setAllowUriQueryParameter(true);
      //post请求解析表单参数
      bearerTokenResolver.setAllowFormEncodedBodyParameter(true);
      return bearerTokenResolver;
  }

//  @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri:http://localhost:9010}")
//  String jwkSetUri;

  @Bean
  JwtDecoder jwtDecoder() {
//    return NimbusJwtDecoder.withJwkSetUri(domain+"/auth").build();
    return NimbusJwtDecoder.withJwkSetUri(domain+"/auth/.well-known/jwks.json").build();
  }
}
