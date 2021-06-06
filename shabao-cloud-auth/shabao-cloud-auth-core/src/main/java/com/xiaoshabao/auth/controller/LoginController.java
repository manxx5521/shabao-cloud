package com.xiaoshabao.auth.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录页
 */
@Controller
public class LoginController {

	/**
	 * 登录页 
	 */
	@GetMapping(value = "/login")
	public String login(ModelMap model, String clientId) {
		model.put("Authorization", getBasicAuthorization(clientId));
		return "login";
	}
	
	/**
   * 登录页 
   */
  @GetMapping(value = "/login",params= {"uri"})
  public String login(ModelMap model, String clientId,String uri) {
    model.put("Authorization", getBasicAuthorization(clientId));
    model.put("uri", uri);
    return "loginToUri";
  }
  
  private String getBasicAuthorization(String clientId) {
    String clientSecret="";
    String src = clientId+ ":" + clientSecret;
    return Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
  }

}
