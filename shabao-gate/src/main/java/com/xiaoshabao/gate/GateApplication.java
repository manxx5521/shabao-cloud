package com.xiaoshabao.gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 路由管理
 * <p>
 * 继承子WebMvcConfigurerAdapter 是为了配置oauth2
 * </p>
 */
// zuul支持
@EnableZuulProxy
// 注册服务发现客户端
@EnableDiscoveryClient
@SpringBootApplication
public class GateApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(GateApplication.class, args);
	}

	/**
	 * 添加自定义的登录和授权界面
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/oauth/confirm_access").setViewName("authorize");

	}

}
