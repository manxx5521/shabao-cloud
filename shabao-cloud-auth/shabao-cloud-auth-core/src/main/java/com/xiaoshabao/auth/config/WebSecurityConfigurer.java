package com.xiaoshabao.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.xiaoshabao.auth.service.MyUserDetailsService;

/**
 * 认证相关配置
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http// 登录页面url 配置登录成功后调用的方法
      .formLogin().loginPage("/login").and().authorizeRequests()
      .mvcMatchers("/.well-known/jwks.json", "/login", "/oauth/**","/static/**").permitAll().anyRequest().authenticated().and()
      .httpBasic().and().csrf().ignoringRequestMatchers((request) -> "/introspect".equals(request.getRequestURI()));
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/static/**");
  }

  /**
   * 注入认证管理器（解决AuthenticationManager注入失败）
   */
  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 通过实现userDetailsService接口类重写loadUserByUsername()方法返回UserDetails进行密码匹配
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    return new MyUserDetailsService();
    //    return new InMemoryUserDetailsManager(
    //      //密码明文：password
    //      //密码密文：{bcrypt}$2a$10$oDghuyu5dAWVrcX/oADYNeQxgmFxA4MB/5wNxZ4xTAt1/GSAxVcI.
    //      User.withDefaultPasswordEncoder().username("subject").password("password").roles("USER").build());
  }

  /*使用密码管理器*/
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
