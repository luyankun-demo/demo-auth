package com.demo.auth.dao.mapper;

import com.demo.auth.bean.domain.RoleInfo;
import com.demo.auth.bean.domain.UserRole;
import com.demo.auth.bean.request.SetRolesReq;
import com.demo.auth.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    void setRoles(SetRolesReq req);

    void deleteRoles(@Param(value = "userId") Integer userId);

    List<RoleInfo> getRoles(@Param(value = "userId")Integer userId);
}
