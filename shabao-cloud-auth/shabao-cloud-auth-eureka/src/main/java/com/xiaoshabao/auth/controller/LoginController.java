package com.xiaoshabao.auth.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录页
 */
@Controller
public class LoginController {

	/**
	 * 登录页 
	 */
	@GetMapping(value = "/login")
	public ModelAndView login(ModelMap model, String clientId,String uri) {
		model.put("Authorization", getBasicAuthorization(clientId));
		 model.put("uri", "http://localhost:8080/admin/callback");
		 model.put("client_id", clientId);
		 model.put("client_secret", "secret");
		return  new ModelAndView("/login", model);
	}
	
  
  private String getBasicAuthorization(String clientId) {
    String clientSecret="secret";
    String src = clientId+ ":" + clientSecret;
    return Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
  }

}
