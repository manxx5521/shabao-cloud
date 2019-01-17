package com.xiaoshabao.gateAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

/**
 * 路由管理 
 * <p>
 * </p>
 */
@SpringBootApplication
@EnableEurekaClient
//开启配置中心
//zuul支持
@EnableZuulProxy
//@EnableConfigServer
public class GateAuthApplication {
	
	/*
	 * 处理request scopes
	 */
	@Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(GateAuthApplication.class, args);
	}

	
}
