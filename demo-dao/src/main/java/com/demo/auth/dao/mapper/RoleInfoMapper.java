package com.demo.auth.dao.mapper;

import com.demo.auth.bean.domain.RoleInfo;
import com.demo.auth.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleInfoMapper extends BaseMapper<RoleInfo> {

    long getRoleInfoCount(@Param(value = "roleName") String roleName, @Param(value = "roleCode") String roleCode);

    List<RoleInfo> getRoleInfoList(
            @Param(value = "roleName") String roleName, @Param(value = "roleCode") String roleCode,
            @Param(value = "pageSize") int pageSize, @Param(value = "offset") int offset);
}
