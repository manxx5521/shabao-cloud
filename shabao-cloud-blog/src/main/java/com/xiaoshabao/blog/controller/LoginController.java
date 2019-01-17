package com.xiaoshabao.blog.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaoshabao.base.controller.BaseController;
import com.xiaoshabao.blog.lang.Consts;

/**
 * 登录页
 */
@Controller
public class LoginController extends BaseController {
	@Value("${security.oauth2.client.clientId}")
	private String clientId;
	@Value("${security.oauth2.client.clientSecret}")
	private String clientSecret;


	/**
	 * 跳转登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String view(@RequestParam(defaultValue = Consts.skin.DEFAULT) String skin, ModelMap model,
			HttpServletRequest request) {
		String src = clientId+ ":" + clientSecret;
		model.put("Authorization", Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8)));
		return skin + Views.LOGIN;
	}

}
