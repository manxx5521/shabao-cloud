package com.xiaoshabao.admin.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置信息
 */
@Component
@ConfigurationProperties("custom.oauth2")
public class OauthConfig {
	
	private Client client;
	private Server server;

	public static class Client {
		@NotNull private String clientId;
		@NotNull private String clientSecret;
		@NotNull private String grantType;
		

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

		public String getGrantType() {
			return grantType;
		}

		public void setGrantType(String grantType) {
			this.grantType = grantType;
		}

	}

	public static class Server {

		@NotNull private String accessTokenUri;
		@NotNull private String userAuthorizationUri;

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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

}
