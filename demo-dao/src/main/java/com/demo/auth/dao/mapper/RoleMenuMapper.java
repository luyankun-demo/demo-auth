package com.demo.auth.dao.mapper;

import com.demo.auth.bean.domain.MenuInfo;
import com.demo.auth.bean.request.SetMenusReq;

import java.util.List;

public interface RoleMenuMapper {

    void deleteMenus(Integer roleId);

    void setMenus(SetMenusReq req);

    List<MenuInfo> getMenus(Integer roleId);
}
