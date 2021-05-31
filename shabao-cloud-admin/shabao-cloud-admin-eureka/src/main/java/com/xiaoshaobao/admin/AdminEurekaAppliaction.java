package com.xiaoshaobao.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.xiaoshabao.base.oauth.annotation.ShaBaoResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@ShaBaoResourceServer
public class AdminEurekaAppliaction {

  public static void main(String[] args) {
    SpringApplication.run(AdminEurekaAppliaction.class, args);
  }

}
