package com.demo.auth.ws.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ShiroConfig {

    /**
     * 配置匹配认证
     * @return
     */
    @Bean
    public CredentialsMatcher getCredentialsMatcher() {
        log.info("配置 getCredentialsMatcher");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(false);
        return hashedCredentialsMatcher;
    }

    /**
     * 认证容器
     */
    @Bean
    public ShiroRealm shiroRealm(CredentialsMatcher credentialsMatcher){
        ShiroRealm shiroRealm =  new ShiroRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        return shiroRealm;
    }

    /**
     * 配置密码认证管理
     * @return
     */
    @Bean
    public PasswordService passwordService(){
        log.info("配置 passwordService");
        return new DefaultPasswordService();
    }

    /**
     * 配置 rememberMeCookie
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        log.info("配置 rememberMeCookie");
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(2592000); // cookie生效时间,单位秒
        return simpleCookie;
    }

    /**
     * 配置rememberMeManager
     * @param rememberMeCookie
     * @return
     */
    @Bean
    public RememberMeManager rememberMeManager(SimpleCookie rememberMeCookie){
        log.info("配置 rememberMeManager");
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie);
        rememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return rememberMeManager;
    }

    /**
     * 配置 sessionIdCookie
     * @return
     */
    @Bean
    public SimpleCookie sessionIdCookie(){
        log.info("配置 sessionIdCookie");
        SimpleCookie simpleCookie = new SimpleCookie("session");
        simpleCookie.setMaxAge(3600); // cookie生效时间,单位秒
        return simpleCookie;
    }

    /**
     * 配置session管理
     * @param sessionIdCookie
     * @return
     */
    @Bean
    public WebSessionManager webSessionManager(SimpleCookie sessionIdCookie){
        log.info("配置 webSessionManager");
        DefaultWebSessionManager webSessionManager = new DefaultWebSessionManager();
        webSessionManager.setSessionIdCookieEnabled(true); // 启用sessionIdCookie
        webSessionManager.setSessionIdCookie(sessionIdCookie); // 配置sessionIdCookie
        return webSessionManager;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     */
    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm, WebSessionManager webSessionManager, RememberMeManager rememberMeManager){
        log.info("配置 securityManager");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm); // 设置登录认证
        securityManager.setSessionManager(webSessionManager); // 设置session管理
        securityManager.setRememberMeManager(rememberMeManager); // 设置rememberMe管理
        return securityManager;
    }


    /**
     * 配置 Filter工厂，设置对应的过滤条件和跳转条件
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            ShiroFilterConfig filterConfig, SecurityManager securityManager){
        log.info("配置 ShiroFilterFactoryBean");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 配置权限管理
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置登录页
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 配置首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 认证不通过跳转路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        // 配置过滤器
        shiroFilterFactoryBean.setFilters(filterConfig.getFilters());
        // 配置过滤条件
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterConfig.getFilterChainDefinitionMap());
        return shiroFilterFactoryBean;
    }


    /**
     * 加入注解的使用，不加入这个注解不生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        log.info("配置 authorizationAttributeSourceAdvisor");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
