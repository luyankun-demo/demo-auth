package com.demo.auth.service.biz.impl;

import com.demo.auth.bean.domain.MenuInfo;
import com.demo.auth.bean.domain.RoleInfo;
import com.demo.auth.bean.request.SetMenusReq;
import com.demo.auth.common.bean.PageBean;
import com.demo.auth.dao.mapper.RoleInfoMapper;
import com.demo.auth.dao.mapper.RoleMenuMapper;
import com.demo.auth.service.biz.RoleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    private RoleInfoMapper roleInfoMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * 获取角色集合(分页)
     * @param roleName
     * @param roleCode
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean getRoleInfoList(String roleName, String roleCode, Integer currentPage, Integer pageSize) {
        PageBean pageBean = new PageBean(currentPage, pageSize);
        long count = roleInfoMapper.getRoleInfoCount(roleName, roleCode);
        List<RoleInfo> list = roleInfoMapper
                .getRoleInfoList(roleName, roleCode, pageBean.getPageSize(), pageBean.getOffset());
        pageBean.setCount(count);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public RoleInfo getRoleInfo(Integer roleId) {
        return roleInfoMapper.getById(roleId);
    }

    @Transactional
    @Override
    public void addRoleInfo(RoleInfo roleInfo) {
        roleInfoMapper.insert(roleInfo);
    }

    @Transactional
    @Override
    public void editRoleInfo(RoleInfo roleInfo) {
        roleInfoMapper.update(roleInfo);
    }

    @Transactional
    @Override
    public void setMenus(SetMenusReq req) {
        log.info("删除当前已存在的关系");
        roleMenuMapper.deleteMenus(req.getRoleId());
        log.info("插入新的角色菜单关系");
        roleMenuMapper.setMenus(req);
    }

    @Override
    public List<MenuInfo> getMenus(Integer roleId) {
        return roleMenuMapper.getMenus(roleId);
    }
}
