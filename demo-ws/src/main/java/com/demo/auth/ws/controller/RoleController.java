package com.demo.auth.ws.controller;

import com.demo.auth.bean.domain.MenuInfo;
import com.demo.auth.bean.domain.RoleInfo;
import com.demo.auth.bean.request.SetMenusReq;
import com.demo.auth.common.bean.PageBean;
import com.demo.auth.common.web.ResponseMessage;
import com.demo.auth.service.biz.RoleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping(value = "/role/")
@RestController
public class RoleController {

    @Autowired
    private RoleInfoService roleInfoService;

    /**
     * 查询角色信息集合
     * @param roleName
     * @param roleCode
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping(value = "getRoleInfoList")
    public ResponseMessage getRoleInfoList(
            @RequestParam(required = false) String roleName,
            @RequestParam(required = false) String roleCode,
            @RequestParam Integer currentPage, @RequestParam Integer pageSize){
        try {
            log.info("查询角色信息集合, 请求参数: roleName:{}, roleCode:{}, currentPage:{}, pageSize:{}",
                    roleName, roleCode, currentPage, pageSize);
            PageBean pageBean = roleInfoService
                    .getRoleInfoList(roleName, roleCode, currentPage, pageSize);
            log.info("查询角色信息集合处理成功");
            return ResponseMessage.success(pageBean);
        } catch (Exception e) {
            log.error("查询角色信息集合, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }

    /**
     * 查询角色详情
     * @param roleId
     * @return
     */
    @GetMapping(value = "getRoleInfo")
    public ResponseMessage getRoleInfo(@RequestParam Integer roleId){
        try {
            log.info("查询角色详情,请求参数:{}", roleId);
            RoleInfo roleInfo = roleInfoService.getRoleInfo(roleId);
            log.info("查询角色详情处理成功");
            return ResponseMessage.success(roleInfo);
        } catch (Exception e) {
            log.error("查询角色详情处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }

    /**
     * 添加角色
     * @return
     */
    @PostMapping(value = "addRoleInfo")
    public ResponseMessage addRoleInfo(@RequestBody RoleInfo roleInfo){
        try {
            log.info("添加角色信息,请求参数:{}", roleInfo);
            roleInfoService.addRoleInfo(roleInfo);
            log.info("添加角色信息处理成功");
            return ResponseMessage.success();
        } catch (Exception e) {
            log.error("添加角色信息处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }

    /**
     * 修改角色
     * @return
     */
    @PostMapping(value = "editRoleInfo")
    public ResponseMessage editRoleInfo(@RequestBody RoleInfo roleInfo){
        try {
            log.info("修改角色信息,请求参数:{}", roleInfo);
            roleInfoService.editRoleInfo(roleInfo);
            log.info("修改角色信息处理成功");
            return ResponseMessage.success();
        } catch (Exception e) {
            log.error("修改角色信息处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }

    /**
     * 查询角色下所有菜单
     * @param roleId
     * @return
     */
    @GetMapping(value = "getMenus")
    public ResponseMessage getMenus(@RequestParam Integer roleId) {
        try {
            log.info("查询角色下所有菜单,请求参数:{}", roleId);
            List<MenuInfo> list = roleInfoService.getMenus(roleId);
            log.info("查询角色下所有菜单处理成功");
            return ResponseMessage.success(list);
        } catch (Exception e) {
            log.error("查询角色下所有菜单处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }

    /**
     * 配置角色菜单集合
     * @return
     */
    @PostMapping(value = "setMenus")
    public ResponseMessage setMenus(@RequestBody SetMenusReq req){
        try {
            log.info("配置角色菜单集合,请求参数:{}", req);
            roleInfoService.setMenus(req);
            log.info("配置角色菜单集合处理成功");
            return ResponseMessage.success();
        } catch (Exception e) {
            log.error("配置角色菜单集合处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }
}
