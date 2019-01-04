package com.xiaoshabao.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.auth.config.OauthConfig;

//@Controller
public class LoginController {
	
	@Autowired
	OauthConfig config;
	
	@RequestMapping("/login")
	@ResponseBody
    public String login(String redirectUri) {
		return "lonin";
    }
	
	@RequestMapping("/authorize")
	@ResponseBody
    public String authorize() {
		return "authorize";
    }
	

}
