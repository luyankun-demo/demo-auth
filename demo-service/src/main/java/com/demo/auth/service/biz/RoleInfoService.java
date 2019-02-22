package com.demo.auth.service.biz;

import com.demo.auth.bean.domain.MenuInfo;
import com.demo.auth.bean.domain.RoleInfo;
import com.demo.auth.bean.request.SetMenusReq;
import com.demo.auth.common.bean.PageBean;

import java.util.List;

public interface RoleInfoService {

    PageBean getRoleInfoList(String roleName, String roleCode, Integer currentPage, Integer pageSize);

    RoleInfo getRoleInfo(Integer roleId);

    void addRoleInfo(RoleInfo roleInfo);

    void editRoleInfo(RoleInfo roleInfo);

    void setMenus(SetMenusReq req);

    List<MenuInfo> getMenus(Integer roleId);
}
