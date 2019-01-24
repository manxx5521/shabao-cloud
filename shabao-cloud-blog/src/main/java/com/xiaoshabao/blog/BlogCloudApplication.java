package com.xiaoshabao.blog;
import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.xiaoshabao.base.BaseApplication;
import com.xiaoshabao.blog.component.DisableUrlSessionFilter;

//缓存对象必须实现Serializable
@EnableCaching
@SpringBootApplication
public class BlogCloudApplication  extends BaseApplication{
	

	public static void main(String[] args) {
		SpringApplication.run(BlogCloudApplication.class, args);
	}

	
	@Bean
    public FilterRegistrationBean<Filter> companyUrlFilterRegister() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        //注入过滤器
        registration.setFilter(new DisableUrlSessionFilter());
        //拦截规则
        registration.addUrlPatterns("/*");
        //过滤器名称
        registration.setName("sessionIdFilter");
        //过滤器顺序
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }
}
