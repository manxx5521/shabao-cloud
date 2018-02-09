package com.xiaoshabao.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.admin.config.OauthConfig;

@Controller
public class LoginController {

	@Autowired
	OauthConfig config;

	@GetMapping("/login")
	@ResponseBody
	public String login(String redirectUri) {

		StringBuilder url = new StringBuilder();
		url.append(config.getServer().getUserAuthorizationUri());
		url.append("?client_id=");
		url.append(config.getClient().getClientId());
		url.append("&client_secret=");
		url.append(config.getClient().getClientSecret());
		url.append("&response_type=");
		url.append(config.getClient().getGrantType());
		url.append("&redirect_uri=");
		url.append(redirectUri);
		// url.append("&state=");
		// url.append("");
		return /*"redirect:" +*/ url.toString();
	}

}
