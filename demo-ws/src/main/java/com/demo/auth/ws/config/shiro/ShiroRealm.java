package com.demo.auth.ws.config.shiro;

import com.demo.auth.bean.model.ShiroPermission;
import com.demo.auth.bean.model.ShiroRole;
import com.demo.auth.bean.model.ShiroUser;
import com.demo.auth.service.biz.UserInfoService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    private static final String LOGIN_KEY = "user";

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CredentialsMatcher credentialsMatcher;

    /**
     * 角色权限和对应权限添加
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("配置角色权限");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> shiroRoles = Sets.newHashSet();
        Set<String> shiroPermissions = Sets.newHashSet();
        ShiroUser shiroUser = getShiroUser();
        if (shiroUser != null) {
            shiroRoles.addAll(shiroUser.getShiroRoles()
                    .stream().map(ShiroRole::getRoleCode).collect(Collectors.toSet()));
            shiroPermissions.addAll(shiroUser.getShiroPermissions()
                    .stream().map(ShiroPermission::getMenuCode).collect(Collectors.toSet()));
        }
        authorizationInfo.setRoles(shiroRoles);
        authorizationInfo.setStringPermissions(shiroPermissions);
        return authorizationInfo;
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        log.info("用户登录认证");
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        log.info("获取用户登录名称:{}", name);
        ShiroUser shiroUser = userInfoService.getShiroUserInfo(name);
        log.info("查询的用户信息为:{}", shiroUser);
        if (shiroUser == null) {
            log.info("没有获取到账户信息:{}", name);
            throw new UnknownAccountException(name);
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                shiroUser.getLoginName(), // 用户名
                shiroUser.getPassword(), // 密码
                ByteSource.Util.bytes(shiroUser.getSalt()), // 记录时间戳
                getName()  //realm name
        );
        boolean match = credentialsMatcher.doCredentialsMatch(authenticationToken, authenticationInfo);
        if (!match) {
            throw new IncorrectCredentialsException();
        }else{
            setSession(LOGIN_KEY, shiroUser);
        }
        return authenticationInfo;
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        super.onLogout(principals);
        setSession(LOGIN_KEY, null);
    }

    private void setSession(String key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            Session sesstion = currentUser.getSession();
            if (sesstion != null) {
                sesstion.setAttribute(key, value);
            }
        }
    }

    /**
     * 获取登录用户
     */
    private ShiroUser getShiroUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            Session session = currentUser.getSession();
            if (session != null)
                return (ShiroUser) session.getAttribute(LOGIN_KEY);
        }
        return null;
    }
}
