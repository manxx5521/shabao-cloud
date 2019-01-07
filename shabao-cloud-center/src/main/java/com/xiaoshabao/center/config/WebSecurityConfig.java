package com.xiaoshabao.center.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//关闭 eureka 的csrf验证 （解决链接不上注册中心问题）
    	http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
