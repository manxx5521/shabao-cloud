package com.xiaoshabao.auth.endpoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 客户端异常处理 1. 可以根据 AuthenticationException 不同细化异常处理
 */
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {
		System.out.println("登录异常");
		authException.printStackTrace();
	}

}
