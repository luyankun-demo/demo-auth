package com.demo.auth.dao.mapper;

import com.demo.auth.bean.domain.MenuInfo;
import com.demo.auth.bean.model.MenuGradeInfo;
import com.demo.auth.common.mapper.BaseMapper;

import java.util.List;

public interface MenuInfoMapper extends BaseMapper<MenuInfo> {

    List<MenuGradeInfo> getMenuInfoAllList();
}
