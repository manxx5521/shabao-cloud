package com.xiaoshabao.upms.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//注册服务发现客户端
@EnableDiscoveryClient
@SpringBootApplication
//扫描当前项目mapper
@MapperScan(basePackages="com.xiaoshabao.upms.server.mapper")
public class UpmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(UpmsApplication.class, args);
	}
}
