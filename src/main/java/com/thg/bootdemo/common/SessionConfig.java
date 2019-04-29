package com.thg.bootdemo.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60*60*24) //maxInactiveIntervalInSeconds: 设置 Session 失效时间（默认是30分支，可以查看源代码），使用 Redis Session 之后，原 Spring Boot 的 server.session.timeout 属性不再生效。
public class SessionConfig {
}
