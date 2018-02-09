package com.xiaoshabao.auth.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.xiaoshabao.auth.config.OauthConfig.Client;

/**
 * 授权认证
 */
@Configuration
// 声明认证服务器
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	/**自定义配置信息*/
	@Autowired
	OauthConfig config;
	// 认证管理器
	@Autowired
	private AuthenticationManager authenticationManager;
//	@Autowired
//	private RedisConnectionFactory connectionFactory;

	// 对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
	// 从表单提交经过OAuth认证
	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		//解决访问oauth/token报401
//		oauthServer.allowFormAuthenticationForClients();
	}

	// 配置OAuth2的客户端相关信息,配置客户端认证方式以及客户端连接参数设置
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		/*
				.withClient(config.getClient().getClientId())
				.secret(config.getClient().getClientSecret())
				// 该client允许的授权类型
				.authorizedGrantTypes("password", "authorization_code", "refresh_token")
				// 允许的授权范围
				.scopes("app");*/
		
		// 使用in-memory存储
		InMemoryClientDetailsServiceBuilder clientDetail=clients.inMemory();
		for(Client detail:config.getClient()) {
			clientDetail.withClient(detail.getClientId())
			.secret(detail.getClientSecret())
			// 该client允许的授权类型
			.authorizedGrantTypes("password", "authorization_code", "refresh_token")
			// 允许的授权范围
			.scopes("app");
		}
		
	}

	// 配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		// @formatter:off
		endpoints.authenticationManager(authenticationManager)
			.accessTokenConverter(accessTokenConverter())
				// .userDetailsService(userDetailsService);
				.tokenStore(tokenStore());// token的保存方式
	}
	
	/**
	 * jwt令牌存储
	 */
	@Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
        	/***
			 * 重写增强token方法,用于自定义一些token返回的信息
			 */
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				String userName = authentication.getUserAuthentication().getName();
				// 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
				User user = (User) authentication.getUserAuthentication().getPrincipal();				
				/** 自定义一些token属性 ***/
				final Map<String, Object> additionalInformation = new HashMap<>();
				additionalInformation.put("userName", userName);
				additionalInformation.put("roles", user.getAuthorities());
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
				OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
				return enhancedToken;
			}
        } ;
        converter.setSigningKey("123");
        return converter;
    }

	/**
	 * 令牌存储
	 * 
	 * @return redis令牌存储对象
	 */
    /*
	@Bean
	public RedisTokenStore tokenStore() {
		return new RedisTokenStore(connectionFactory);
	}*/

}