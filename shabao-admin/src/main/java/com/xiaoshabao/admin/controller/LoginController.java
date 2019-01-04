package com.xiaoshabao.admin.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.xiaoshabao.admin.config.OauthConfig;

import springfox.documentation.annotations.ApiIgnore;

@Controller
public class LoginController {

	@Autowired
	OauthConfig config;

	@GetMapping("/login")
	public String login(@ApiIgnore() ModelMap model, HttpServletRequest request, String redirectUri) {
		Map<String, Object> params = new HashMap<String, Object>();
		Enumeration<String> paramnames = request.getParameterNames();
		while (paramnames.hasMoreElements()) {
			String paramname = paramnames.nextElement();
			params.put(paramname, request.getParameter(paramname));
		}

		String src=config.getClient().getClientId() + ":" + config.getClient().getClientSecret();
		model.put("Authorization", Base64.getEncoder().encodeToString(
				src.getBytes(StandardCharsets.UTF_8)));

		return "/login";
	}

	@GetMapping("/index")
	public String index() {
		return "success";
	}
	/*
	 * @GetMapping("/login")
	 * 
	 * @ResponseBody public String login(String redirectUri) {
	 * 
	 * StringBuilder url = new StringBuilder();
	 * url.append(config.getServer().getUserAuthorizationUri());
	 * url.append("?client_id="); url.append(config.getClient().getClientId());
	 * url.append("&client_secret=");
	 * url.append(config.getClient().getClientSecret());
	 * url.append("&response_type="); url.append(config.getClient().getGrantType());
	 * url.append("&redirect_uri="); url.append(redirectUri); //
	 * url.append("&state="); // url.append(""); return "redirect:" +
	 * url.toString(); }
	 */
}
