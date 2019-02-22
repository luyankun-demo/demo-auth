package com.demo.auth.ws.controller;

import com.demo.auth.bean.domain.MenuInfo;
import com.demo.auth.bean.model.MenuGradeInfo;
import com.demo.auth.common.web.ResponseMessage;
import com.demo.auth.service.biz.MenuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping(value = "/menu/")
@RestController
public class MenuController {

    @Autowired
    private MenuInfoService menuInfoService;

    /**
     * 查询全部菜单结构集合
     * @return
     */
    @GetMapping(value = "getMenuInfoAllList")
    public ResponseMessage getMenuInfoAllList() {
        try {
            log.info("查询全部菜单结构集合");
            List<MenuGradeInfo> list = menuInfoService.getMenuInfoAllList();
            return ResponseMessage.success(list);
        } catch (Exception e) {
            log.error("查询全部菜单结构集合处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }

    /**
     * 添加菜单信息
     * @param menuInfo
     * @return
     */
    @PostMapping(value = "addMenuInfo")
    public ResponseMessage addMenuInfo(MenuInfo menuInfo){
        try {
            log.info("添加菜单信息,请求参数:{}", menuInfo);
            menuInfoService.addMenuInfo(menuInfo);
            log.info("添加菜单信息处理成功");
            return ResponseMessage.success();
        } catch (Exception e) {
            log.error("添加菜单信息处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }

    /**
     * 编辑菜单信息
     * @param menuInfo
     * @return
     */
    @PostMapping(value = "editMenuInfo")
    public ResponseMessage editMenuInfo(MenuInfo menuInfo){
        try {
            log.info("编辑菜单信息,请求参数:{}", menuInfo);
            menuInfoService.editMenuInfo(menuInfo);
            log.info("编辑菜单信息处理成功");
            return ResponseMessage.success();
        } catch (Exception e) {
            log.error("编辑菜单信息处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }

    /**
     * 查询菜单信息详情
     * @param id
     * @return
     */
    @GetMapping(value = "getMenuInfo")
    public ResponseMessage getMenuInfo(@RequestParam Integer id){
        try {
            log.info("查询菜单信息详情,请求参数:{}", id);
            MenuInfo menuInfo = menuInfoService.getMenuInfo(id);
            log.info("查询菜单信息详情处理成功");
            return ResponseMessage.success(menuInfo);
        } catch (Exception e) {
            log.error("查询菜单信息详情处理失败, 捕获异常:{}", e);
            return ResponseMessage.fail();
        }
    }
}
