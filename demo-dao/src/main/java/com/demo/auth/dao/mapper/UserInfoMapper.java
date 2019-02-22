package com.demo.auth.dao.mapper;

import com.demo.auth.bean.domain.UserInfo;
import com.demo.auth.bean.model.ShiroUser;
import com.demo.auth.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    ShiroUser getShiroUserInfo(@Param(value = "name") String name);

    long getUserInfoCount(@Param(value = "loginName") String loginName, @Param(value = "userName") String userName);

    List<UserInfo> getUserInfoList(
            @Param(value = "loginName") String loginName, @Param(value = "userName") String userName,
            @Param(value = "pageSize") Integer pageSize, @Param(value = "offset") Integer offset);
}
