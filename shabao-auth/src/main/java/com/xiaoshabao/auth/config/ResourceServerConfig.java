package com.xiaoshabao.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
/*
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling()
				.authenticationEntryPoint(
						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and().authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}*/
	/*
	// 配置安全策略
		@Override
		public void configure(HttpSecurity http) throws Exception {
			// 禁掉csrf
			http.csrf().disable()
					// 登录页面url 配置登录成功后调用的方法
					.formLogin().loginPage("/login").permitAll()
					.and().authorizeRequests()
					// 配置这些链接无需验证
					.antMatchers("/**")
					.anonymous().and().authorizeRequests()
					// 除以上路径都需要验证
					.anyRequest().authenticated()
			// 路径角色验证
			// .antMatchers("/admin/**").hasRole("ADMIN")
			// 排除该路径角色认证 注意顺序自上而下
			// .antMatchers("/**").hasRole("USER")
			;
		}*/
}
