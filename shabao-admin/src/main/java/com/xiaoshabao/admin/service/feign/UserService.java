package com.xiaoshabao.admin.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaoshabao.upms.client.entity.UserEntity;

@FeignClient(value = "shabao-upms",fallback = UserServiceHystric.class)
public interface UserService {
	
	@GetMapping(value = "/user/{userId}")
	UserEntity getUserById(@RequestParam(value = "userId") String userId);

}
