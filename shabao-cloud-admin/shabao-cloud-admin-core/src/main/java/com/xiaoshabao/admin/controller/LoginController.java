package com.xiaoshabao.admin.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.MimeHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.minidev.json.JSONObject;

/**
 * 登录页
 */
@Controller
public class LoginController {

  @Value("${spring.application.name}")
  private String clientId;

  @Value("${app.domain}")
  private String domain;

  /**
   * 跳转登录页
   */
  @RequestMapping(value = "/login")
  public ModelAndView login(ModelMap model, HttpServletRequest request, RedirectAttributes attr) {
    attr.addAttribute("clientId", clientId);
    return new ModelAndView("redirect:" + domain + "/login", model);
  }

  /**
   * 跳转登录页
   */
  @RequestMapping(value = "/callback")
  public ModelAndView callBack(ModelMap model, HttpServletRequest request, HttpServletResponse response,
    RedirectAttributes attr, String access_token) {
    response.addHeader("Authorization", "Bearer " + access_token);

    RestTemplate restTemplate = new RestTemplate();

    // 声明一个header变量
    HttpHeaders headers = new HttpHeaders();
    // 设置为异步请求
    headers.set("Authorization", "Bearer " + access_token);

    HttpEntity entity = new HttpEntity(headers);

    // ResponseEntity封装了返回的数据，包括了request、body、header等
    try {
      ResponseEntity<String> resut = restTemplate.exchange(domain + "/admin/index", HttpMethod.GET, entity, String.class);
      System.out.println();
    } catch (Exception e) {
      e.printStackTrace();
    }
    

    // 修改请求头
    Map<String, String> map = new HashMap<>();
    map.put("Authorization", "Bearer " + access_token);
    modifyHeaders(map, request);

    return new ModelAndView("redirect:" + domain + "/admin/index", model);
  }

  /**
   * 修改请求头信息
   * @param headerses
   * @param request
   */
  private void modifyHeaders(Map<String, String> headerses, HttpServletRequest request) {
    if (headerses == null || headerses.isEmpty()) {
      return;
    }
    Class<? extends HttpServletRequest> requestClass = request.getClass();
    try {
      Field request1 = requestClass.getDeclaredField("request");
      request1.setAccessible(true);
      Object o = request1.get(request);
      Field coyoteRequest = o.getClass().getDeclaredField("coyoteRequest");
      coyoteRequest.setAccessible(true);
      Object o1 = coyoteRequest.get(o);
      Field headers = o1.getClass().getDeclaredField("headers");
      headers.setAccessible(true);
      MimeHeaders o2 = (MimeHeaders) headers.get(o1);
      for (Map.Entry<String, String> entry : headerses.entrySet()) {
        o2.removeHeader(entry.getKey());
        o2.addValue(entry.getKey()).setString(entry.getValue());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
