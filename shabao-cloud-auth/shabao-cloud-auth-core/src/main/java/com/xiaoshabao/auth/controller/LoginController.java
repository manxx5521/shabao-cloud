package com.xiaoshabao.auth.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.auth.service.RedisClientDetailsService;

/**
 * 登录页
 */
@Controller
public class LoginController {

  @Autowired
  private RedisClientDetailsService RedisClientDetailsService;
  
  @Value("${app.domain}")
  private String domain;

  /**
   * 登录页 
   */
  @GetMapping(value = "/login")
  public ModelAndView login(ModelMap model, String clientId, String uri) {
    model.put("Authorization", getBasicAuthorization(clientId));

    if(uri!=null&&!uri.isEmpty()) {
      model.put("uri",uri);
    }else {
      Set<String> uris = RedisClientDetailsService.loadClientByClientId(clientId).getRegisteredRedirectUri();
      if (uris != null && !uris.isEmpty()) {
         String url=uris.iterator().next();
         if(!url.startsWith("http")) {
           url=domain+url;
         }
        model.put("uri", url);
      }
    }
    model.put("client_id", clientId);
    model.put("client_secret", "secret");
    return new ModelAndView("/login", model);
  }

  private String getBasicAuthorization(String clientId) {
    String clientSecret = "secret";
    String src = clientId + ":" + clientSecret;
    return Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
  }

}
