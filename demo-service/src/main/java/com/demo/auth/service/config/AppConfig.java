package com.demo.auth.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 */
@PropertySource(value = {"classpath:config-biz/application-${spring.profiles.active}.yml"})
@Configuration
public class AppConfig {
}
