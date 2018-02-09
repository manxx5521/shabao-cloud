package com.xiaoshabao.upms.server.service;

import com.xiaoshabao.upms.client.entity.UserEntity;

public interface UserService {
	
	UserEntity findUserById(Integer userId);

}
