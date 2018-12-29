package com.xiaoshabao.upms.server.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoshabao.upms.client.entity.UserEntity;

@RestController
@RequestMapping("/user")
public class UserController {
//	@Autowired
//	private UserService userService;

	@GetMapping("/{userId}")
	public UserEntity findUserById(@PathVariable("userId") Integer userId) {
		/*
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		UserEntity user=new UserEntity();
		user.setUserName("张三");
		user.setPassword("1111");
		return user;
//		return userService.findUserById(userId);
	}

}
