package com.demo.auth.ws.config.shiro;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Component
public class ShiroFilterConfig {

    private Map<String, String> filterChainDefinitionMap;
    private Map<String, Filter> filters;

    public ShiroFilterConfig() {
        log.info("构建shiro过滤配置....");
        initFilterChainDefinitionMap();
        initFilter();
    }

    private void initFilterChainDefinitionMap(){
        this.filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/welcome", "anon");
        filterChainDefinitionMap.put("/error", "anon");
        filterChainDefinitionMap.put("/api-docs/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/hello/**", "anon");
        filterChainDefinitionMap.put("/test", "anon");
        filterChainDefinitionMap.put("/403", "anon");
        filterChainDefinitionMap.put("/404", "anon");
        filterChainDefinitionMap.put("/500", "anon");

        filterChainDefinitionMap.put("/auth", "anon");
    }

    private void initFilter(){
        this.filters = new HashMap<>();
        filters.put("anon", new AnonymousFilter());
        filters.put("authc", new FormAuthenticationFilter());
        filters.put("logout", new LogoutFilter());
        filters.put("roles", new RolesAuthorizationFilter());
        filters.put("perms", new PermissionsAuthorizationFilter());
        filters.put("user", new UserFilter());
    }
}
