package com.xiaoshabao.admin.service.feign;

import org.springframework.stereotype.Component;

import com.xiaoshabao.upms.client.entity.UserEntity;

@Component
public class UserServiceHystric implements UserService {

	@Override
	public UserEntity getUserById(String userId) {
		return null;
	}
	
	

}
