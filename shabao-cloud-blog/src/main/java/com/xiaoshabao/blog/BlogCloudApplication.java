package com.xiaoshabao.blog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.xiaoshabao.base.BaseApplication;

//缓存对象必须实现Serializable
@EnableCaching
@SpringBootApplication
public class BlogCloudApplication  extends BaseApplication{
	

	public static void main(String[] args) {
		SpringApplication.run(BlogCloudApplication.class, args);
	}

}
