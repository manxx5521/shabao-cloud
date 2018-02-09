package com.xiaoshabao.upms.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.upms.client.entity.UserEntity;
import com.xiaoshabao.upms.server.mapper.UserMapper;
import com.xiaoshabao.upms.server.service.UserService;
@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserEntity findUserById(Integer userId) {
		return userMapper.getUserById(userId);
	}

}
