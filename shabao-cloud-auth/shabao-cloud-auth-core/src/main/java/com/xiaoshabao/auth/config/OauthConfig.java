package com.xiaoshabao.auth.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置信息
 */
@Component
@ConfigurationProperties("custom.oauth2")
public class OauthConfig {
	
	private List<Client> client;
	private Server server;

	public static class Client {
		private String clientId;
		private String clientSecret;

		public String getClientId() {
			return clientId;
		}

		public void setClientId(String clientId) {
			this.clientId = clientId;
		}

		public String getClientSecret() {
			return clientSecret;
		}

		public void setClientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
		}
	}

	public static class Server {

		private String accessTokenUri;
		private String userAuthorizationUri;

		public String getAccessTokenUri() {
			return accessTokenUri;
		}

		public void setAccessTokenUri(String accessTokenUri) {
			this.accessTokenUri = accessTokenUri;
		}

		public String getUserAuthorizationUri() {
			return userAuthorizationUri;
		}

		public void setUserAuthorizationUri(String userAuthorizationUri) {
			this.userAuthorizationUri = userAuthorizationUri;
		}

	}

	public void setServer(Server server) {
		this.server = server;
	}

	public List<Client> getClient() {
		return client;
	}

	public void setClient(List<Client> client) {
		this.client = client;
	}

	public Server getServer() {
		return server;
	}
	
}
