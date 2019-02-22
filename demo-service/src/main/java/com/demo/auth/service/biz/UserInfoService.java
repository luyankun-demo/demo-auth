package com.demo.auth.service.biz;

import com.demo.auth.bean.domain.RoleInfo;
import com.demo.auth.bean.domain.UserInfo;
import com.demo.auth.bean.model.ShiroUser;
import com.demo.auth.bean.request.SetRolesReq;
import com.demo.auth.common.bean.PageBean;

import java.util.List;

public interface UserInfoService {

    ShiroUser getShiroUserInfo(String name);

    void registerUserInfo(UserInfo userInfo);

    void editUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(Integer id);

    void setRoles(SetRolesReq req);

    PageBean getUserInfoList(String loginName, String userName, Integer currentPage, Integer pageSize);

    List<RoleInfo> getRoles(Integer userId);
}
