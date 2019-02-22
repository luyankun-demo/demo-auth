package com.demo.auth.service.biz.impl;

import com.demo.auth.bean.domain.RoleInfo;
import com.demo.auth.bean.domain.UserInfo;
import com.demo.auth.bean.model.ShiroUser;
import com.demo.auth.bean.request.SetRolesReq;
import com.demo.auth.common.bean.PageBean;
import com.demo.auth.dao.mapper.UserInfoMapper;
import com.demo.auth.dao.mapper.UserRoleMapper;
import com.demo.auth.service.biz.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * Shiro 登录验证查询
     * @param name
     * @return
     */
    @Override
    public ShiroUser getShiroUserInfo(String name) {
        return userInfoMapper.getShiroUserInfo(name);
    }

    /**
     * 注册用户信息
     * @param userInfo
     */
    @Transactional
    @Override
    public void registerUserInfo(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }

    /**
     * 编辑用户信息
     * @param userInfo
     */
    @Transactional
    @Override
    public void editUserInfo(UserInfo userInfo) {
        userInfoMapper.update(userInfo);
    }

    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    @Override
    public UserInfo getUserInfo(Integer id) {
        return userInfoMapper.getById(id);
    }

    /**
     * 用户设置角色
     * @param req
     */
    @Transactional
    @Override
    public void setRoles(SetRolesReq req) {
        // 删除已存在的角色
        userRoleMapper.deleteRoles(req.getUserId());
        // 设置新的角色
        userRoleMapper.setRoles(req);
    }

    /**
     * 获取用户信息集合
     * @param loginName
     * @param userName
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean getUserInfoList(String loginName, String userName, Integer currentPage, Integer pageSize) {
        PageBean pageBean = new PageBean(currentPage, pageSize);
        long count = userInfoMapper.getUserInfoCount(loginName, userName);
        List<UserInfo> list = userInfoMapper
                .getUserInfoList(loginName, userName, pageBean.getPageSize(), pageBean.getOffset());
        pageBean.setCount(count);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public List<RoleInfo> getRoles(Integer userId) {
        return userRoleMapper.getRoles(userId);
    }
}
