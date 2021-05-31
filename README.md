<p align="center">
 <img src="https://img.shields.io/badge/Spring%20Cloud-2020-blue.svg" alt="Coverage Status">
 <img src="https://img.shields.io/badge/Spring%20Boot-2.5-blue.svg" alt="Downloads">
</p>

## 系统说明

- 基于 Spring Cloud 2020 、Spring Boot 2.4、 OAuth2 的 RBAC **权限管理系统**
- 提供对常见容器化支持 Docker支持

### 核心依赖

| 依赖                   | 版本          |
| ---------------------- | ------------- |
| Spring Boot            | 2.5.0 |
| Spring Cloud           | 2020.0.3    |
| Spring Cloud Alibaba   | 2021.1|
| Spring Security OAuth2 | 2.3.6         |
| Mybatis Plus           | 3.4.3         |

### 模块说明
```lua
shabao-base  -- 基础模块https://github.com/manxx5521/shabao-base.git
├── shabao-base-common -- 基础类，方法
├── shabao-base-mybatis -- 通用mybatis实现
└── shabao-base-oauth-resource -- 通用oauth2资源服务器实现

shabao-cloud
└── shabao-cloud-admin -- 程序管理服务[9030]
     ├── shabao-cloud-admin-core -- 核心包
     └── shabao-cloud-admin-eureka -- eureka实现
└── shabao-cloud-auth -- 程序管理服务[9010]
     ├── shabao-cloud-auth-core -- 核心包
     └── shabao-cloud-auth-eureka -- eureka实现
├── shabao-cloud-gateway -- 网关服务[8080]
└── shabao-cloud-register -- 注册中心
     ├── shabao-cloud-register-eureka -- eureka实现[8761]
     └── shabao-cloud-register-nacos -- nacos实现[8848]
└── shabao-cloud-config -- 网关服务[8090]

shabao-upms -- 通用用户权限管理模块[9020]
     ├── shabao-upms-cloud -- 微服务实现
     └── shabao-upms-core -- 核心包
     
shabao-blog -- 博客系统
     ├── shabao-blog-cloud -- 微服务实现[10000]
     ├── shabao-blog-core -- 核心包
     └── shabao-blog-shiro -- 单体架构
```
 
