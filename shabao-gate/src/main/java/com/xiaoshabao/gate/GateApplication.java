package com.xiaoshabao.gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * 路由管理
 * <p>
 * </p>
 */
@RestController
@SpringBootApplication
@EnableEurekaClient
public class GateApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> 
					p.path("/get")//匹配所有/get 请求
					.filters(f -> f.addRequestHeader("Hello", "World"))//添加heard信息
					.uri("http://httpbin.org:80"))
				/*.route(p -> p
					.host("*.hystrix.com")//匹配 host有“*.hystrix.com”，都会进入该router
				    .filters(f -> f
				    	.hystrix(config -> config//服务熔断降级
				    		.setName("mycmd")//配置名称
				            .setFallbackUri("forward:/fallback")))//指向性fallback的逻辑的地址
				     .uri("http://httpbin.org:80"))*/
				.build();
	}
	
	@RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }
}
