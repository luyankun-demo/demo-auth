package com.demo.auth.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 持久层配置
 */
@PropertySource(value = {"classpath:config-dao/application-${spring.profiles.active}.yml"})
@MapperScan(basePackages = {"com.demo.auth.dao.mapper"})
@EnableTransactionManagement
@Configuration
public class AppConfig {
}
