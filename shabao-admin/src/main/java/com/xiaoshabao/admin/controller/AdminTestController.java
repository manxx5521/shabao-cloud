package com.xiaoshabao.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xiaoshabao.upms.client.entity.UserEntity;

@RestController
@RequestMapping("/test")
//刷新配置 手动刷新 用post方法访问http://localhost:9002/refresh
@RefreshScope
public class AdminTestController {
	@Autowired
	RestTemplate restTemplate;

	@Value("${domain}")
	private String domain;

	// 断路器支持
	@HystrixCommand(fallbackMethod = "testError")
	@GetMapping
//	@PreAuthorize("hasAuthority('query-demo')")
	public UserEntity test(String userId) {
		UserEntity user = restTemplate.getForObject("http://SHABAO-UPMS/user/12345678", UserEntity.class);
		return user;
	}

	/**
	 * 断路器支持
	 * <p>
	 * 必须保证参数相同,如果访问超时进入此方法
	 * </p>
	 */
	public UserEntity testError(String userId) {
		UserEntity user = new UserEntity();
		user.setUserId(1);
		user.setUserName("失败");
		return user;
	}

	/**
	 * 测试配置中心配置
	 * 
	 * @return
	 */
	@GetMapping("/domain")
	public String getDomain() {
		return this.domain;
	}
}
