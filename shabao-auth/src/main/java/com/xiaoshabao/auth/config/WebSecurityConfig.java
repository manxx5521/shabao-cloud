package com.xiaoshabao.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 配置认证管理器，以及安全策略
 */
@Configuration
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * 注入认证管理器（解决AuthenticationManager注入失败）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception { 
	        http.csrf().disable()
	        	.requestMatchers()
	            .antMatchers("/login", "/oauth/authorize","/oauth/token")
	            .and()
	            .authorizeRequests()
	            .anyRequest()
	            .authenticated()
	            .and()
	            // 登录页面url 配置登录成功后调用的方法
				.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("http://localhost:8080/shabao-admin/index")
//					.successHandler(new LoginAuthenticationHandler())//登录之后处理
//					.failureHandler(new LoginAuthenticationHandler())//登录失败后处理
	            .permitAll();
	    }
	 
	// 配置认证管理器
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			// 通过实现userDetailsService接口类重写loadUserByUsername()方法返回UserDetails进行密码匹配
			auth.userDetailsService(springDataUserDetailsService()).passwordEncoder(passwordEncoder());
		}
		
		/**
		 * 自定义登录验证类
		 * @return
		 */
		@Bean
		public UserDetailsService springDataUserDetailsService() {
		    return new MyUserDetailsService();
		}
		
		/**
		 * 使用密码管理器
		 */
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
		    return new BCryptPasswordEncoder();
		}

	
}
