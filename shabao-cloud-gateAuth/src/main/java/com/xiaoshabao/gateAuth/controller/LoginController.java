package com.xiaoshabao.gateAuth.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登录页
 */
@Controller
public class LoginController {

	/**
	 * 跳转登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model, String clientId,String clientSecret) {
		String src = clientId+ ":" + clientSecret;
		model.put("Authorization", Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8)));
		return "login";
	}

}
