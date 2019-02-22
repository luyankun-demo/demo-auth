package com.demo.auth.ws.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping(value = "")
@Controller
public class IndexController {

    @GetMapping(value = "/login")
    public String login(){
        return "/login";
    }


    @GetMapping(value = "/index")
    public String index(){
        return "/index";
    }
    /**
     *用户登录认证
     * @param loginName 登录名
     * @param password 密码
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/auth")
    public String auth(@RequestParam String loginName, @RequestParam String password) {
        log.info("开始登录验证, 登录名称:{}", loginName);
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        /*
         * 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
         * 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
         * 所以这一步在调用login(token)方法时,它会走到自定义ShiroRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
         */
        try {
            currentUser.login(token);
        }catch(UnknownAccountException uae){
            log.info("用户[{}]进行登录验证未通过,未知账户", loginName);
        }catch(IncorrectCredentialsException ice){
            log.info("用户[{}]进行登录验证未通过,错误的凭证", loginName);
        }catch(LockedAccountException lae){
            log.info("用户[{}]进行登录验证未通过,账户已锁定", loginName);
        }catch(ExcessiveAttemptsException eae){
            log.info("用户[{}]进行登录验证未通过,错误次数过多", loginName);
        }catch(AuthenticationException ae){
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("用户[{}]进行登录验证未通过,堆栈轨迹如下", loginName);
        }
        if (currentUser.isAuthenticated()) {
            log.info("用户[{}]登录成功", loginName);
            return "redirect:/index";
        } else {
            log.info("用户[{}]登录失败", loginName);
            token.clear();
            return "redirect:/login";
        }
    }
}
