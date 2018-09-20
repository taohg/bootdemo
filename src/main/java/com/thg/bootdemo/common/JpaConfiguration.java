package com.thg.bootdemo.common;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description JPA基础配置类
 * 						1.创建项目 
 *						2.添加依赖，填写配置类
 *							@Order声明了组件加载的顺序，其中接受一个整形值，值越低约先加载；
 *                          @Configuration声明了这是一个配置类，该注解中包含有@Component注解，可以让SpringBoot自动扫描加载；
 *                          @EnableTransactionManagement声明了开启事务管理器代理；
 *                          @EnableJpaRepositories声明repository（也就是原来的dao，SpringData中称其为Repository）所在位置，值中的两个星号是通配符，代表com.thg.bootdemo.任何路径下的.repository包中都是repository；
 *                          @EntityScan是对实体组件位置的声明与扫描，两个星号依旧是通配符
 *						3.配置application.properties
 *						4.配置实体jpa关系
 *                          注意：实体类中引用的注解大多都是javax.persistence包下的
 *						5.继承jpaRepository
 *						6.编写启动测试用例
 * @author taohg
 *
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
@EnableJpaRepositories(basePackages={"com.thg.bootdemo.repository"})
@EntityScan(basePackages={"com.thg.bootdemo.entity"})
public class JpaConfiguration {
	@Bean
	PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
		return new  PersistenceExceptionTranslationPostProcessor();
	}
}
