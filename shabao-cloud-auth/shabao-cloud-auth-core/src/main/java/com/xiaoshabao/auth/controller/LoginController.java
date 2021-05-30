package com.xiaoshabao.auth.controller;

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
	public String login(ModelMap model, String authorization,String uri) {
		model.put("Authorization", authorization);
		model.put("uri", uri);
		return "login";
	}

}
