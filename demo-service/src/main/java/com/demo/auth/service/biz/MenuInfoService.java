package com.demo.auth.service.biz;

import com.demo.auth.bean.domain.MenuInfo;
import com.demo.auth.bean.model.MenuGradeInfo;

import java.util.List;

public interface MenuInfoService {

    List<MenuGradeInfo> getMenuInfoAllList();

    void addMenuInfo(MenuInfo menuInfo);

    void editMenuInfo(MenuInfo menuInfo);

    MenuInfo getMenuInfo(Integer id);
}
