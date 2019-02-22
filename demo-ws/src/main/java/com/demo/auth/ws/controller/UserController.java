package com.demo.auth.ws.controller;

import com.demo.auth.bean.domain.RoleInfo;
import com.demo.auth.bean.domain.UserInfo;
import com.demo.auth.bean.request.SetRolesReq;
import com.demo.auth.common.bean.PageBean;
import com.demo.auth.common.utils.DateUtils;
import com.demo.auth.common.web.ResponseMessage;
import com.demo.auth.service.biz.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "index")
    public String index() {
        log.info("打开用户管理主页面...");
        return "/user/index";
    }

    @ResponseBody
    @GetMapping(value = "getUserInfoList")
    public ResponseMessage getUserInfoList(
            @RequestParam(required = false) String loginName,
            @RequestParam(required = false) String userName,
            @RequestParam Integer currentPage, @RequestParam Integer pageSize) {
        log.info("查询用户信息, 请求参数: loginName:{}, userName:{}, currentPage:{}, pageSize:{}",
                loginName, userName, currentPage, pageSize);
        PageBean pageBean = userInfoService.getUserInfoList(loginName, userName, currentPage, pageSize);
        return ResponseMessage.success(pageBean);
    }

    @ResponseBody
    @GetMapping(value = "getUserInfo")
    public ResponseMessage getUserInfo(@RequestParam Integer id) {
        log.info("查询用户信息, 请求参数:{}", id);
        UserInfo userInfo = userInfoService.getUserInfo(id);
        return ResponseMessage.success(userInfo);
    }

    @GetMapping(value = "register")
    public String register(){
        return "/user/register";
    }

    /**
     * 用户注册
     * @param userInfo
     * @return
     */
    @ResponseBody
    @PostMapping(value = "registerUserInfo")
    public ResponseMessage registerUserInfo(@RequestBody UserInfo userInfo){
        log.info("注册用户信息,请求消息:{}", userInfo);
        try {
            String salt = DateUtils.getCurrentDatetimeSecondNotFormat();
            String password = new Sha256Hash(userInfo.getPassword(), salt).toBase64();
            userInfo.setSalt(salt);
            userInfo.setPassword(password);
            userInfoService.registerUserInfo(userInfo);
            log.info("注册用户信息处理成功");
            return ResponseMessage.success();
        } catch (Exception e) {
            log.error("注册用户信息处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }

    /**
     * 编辑用户信息
     * @param userInfo
     * @return
     */
    @ResponseBody
    @PostMapping(value = "editUserInfo")
    public ResponseMessage editUserInfo(@RequestBody UserInfo userInfo){
        log.info("编辑用户信息,请求消息:{}", userInfo);
        try {
            userInfoService.editUserInfo(userInfo);
            log.info("编辑用户信息处理成功");
            return ResponseMessage.success();
        } catch (Exception e) {
            log.error("编辑用户信息处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }

    /**
     * 获取用户角色信息
     * @param userId
     * @return
     */
    @ResponseBody
    @GetMapping(value = "getRoles")
    public ResponseMessage getRoles(@RequestParam Integer userId){
        log.info("获取用户角色信息,请求消息:{}", userId);
        try {
            List<RoleInfo> list = userInfoService.getRoles(userId);
            log.info("获取用户角色信息处理成功");
            return ResponseMessage.success(list);
        } catch (Exception e) {
            log.error("获取用户角色信息处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }

    /**
     * 配置用户角色
     * @param req
     * @return
     */
    @ResponseBody
    @PostMapping(value = "setRoles")
    public ResponseMessage setRoles(@RequestBody SetRolesReq req){
        log.info("配置用户角色,请求消息:{}", req);
        try {
            userInfoService.setRoles(req);
            log.info("配置用户角色处理成功");
            return ResponseMessage.success();
        } catch (Exception e) {
            log.error("配置用户角色处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }
}
