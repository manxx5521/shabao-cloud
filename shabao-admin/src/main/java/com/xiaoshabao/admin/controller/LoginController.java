package com.xiaoshabao.admin.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.xiaoshabao.admin.config.OauthConfig;

import springfox.documentation.annotations.ApiIgnore;

@Controller
public class LoginController {
	@Value("${security.oauth2.client.clientId}")
	private String clientId;
	@Value("${security.oauth2.client.clientSecret}")
	private String clientSecret;

	@Autowired
	OauthConfig config;

	@GetMapping("/login")
	public String login(@ApiIgnore() ModelMap model, HttpServletRequest request, String redirectUri) {
		String src=clientId + ":" + clientSecret;
		model.put("Authorization", Base64.getEncoder().encodeToString(
				src.getBytes(StandardCharsets.UTF_8)));
		return "/login";
	}

	@GetMapping("/index")
	public String index() {
		return "success";
	}
}
