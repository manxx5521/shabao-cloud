package com.xiaoshabao.upms.server.mapper;

import com.xiaoshabao.upms.client.entity.UserEntity;

public interface UserMapper {

	/**
	 * 根据id获得实体
	 * 
	 * @param userId
	 * @return
	 */
	UserEntity getUserById(Integer userId);

}
