package com.xiaoshabao.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoshabao.auth.config.OauthConfig;

@Controller
public class LoginController {
	
	@Autowired
	OauthConfig config;
	
	@RequestMapping("/login")
    public String login(String redirectUri) {
		
		return "lonin";
    }
	
	@RequestMapping("/authorize")
    public String authorize() {
		
		return "authorize";
    }
	

}
