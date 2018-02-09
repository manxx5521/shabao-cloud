package com.xiaoshabao.upms.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoshabao.upms.client.entity.UserEntity;
import com.xiaoshabao.upms.server.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public UserEntity findUserById(@PathVariable("userId") Integer userId) {
		/*
		//用来模仿链接超时
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
		}*/
		return userService.findUserById(userId);
	}

}
