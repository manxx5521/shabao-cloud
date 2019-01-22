package com.xiaoshabao.gateAuth.config;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户信息获取方法
 *	<p>实现 UserDetailsService 接口查询用户验证信息</p> 
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	MyUserDetailDao dao;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		UserInfo userInfo=dao.getUser(name);
		if(userInfo==null) {
			throw  new UsernameNotFoundException("用户["+name+"]不存在");
		}
		Collection<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("admin"));// 用户所拥有的角色信息
		
		/*BCryptPasswordEncoder encode = new BCryptPasswordEncoder();  
		String password = encode.encode("qwer1234");*/
		User user = new User(userInfo.getUserName(), userInfo.getPassword(), authorities);
		return user;

		
	}

}
