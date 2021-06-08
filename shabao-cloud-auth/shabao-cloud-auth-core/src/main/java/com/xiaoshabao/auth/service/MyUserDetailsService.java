package com.xiaoshabao.auth.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 用户信息获取方法
 *	<p>实现 UserDetailsService 接口查询用户验证信息</p> 
 */
@Component
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
		dao.getRole(userInfo.getUserId()).forEach(role->{
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleValue()));// 用户所拥有的角色信息,角色信息以ROLE_开头
			dao.getPermission(role.getRoleId()).forEach(permission->{
				authorities.add(new SimpleGrantedAuthority(permission.getPermissionValue()));//存放权限信息
			});
		});
		User user = new User(userInfo.getUserName(), userInfo.getPassword(), authorities);
		return user;

		
	}

}