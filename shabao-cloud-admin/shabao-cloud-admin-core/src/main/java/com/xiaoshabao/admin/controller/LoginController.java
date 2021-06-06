package com.xiaoshabao.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
  public ModelAndView view(ModelMap model, HttpServletRequest request, RedirectAttributes attr) {
    attr.addAttribute("clientId", clientId);
    return new ModelAndView("redirect:" + domain + "/login", model);
  }

}
