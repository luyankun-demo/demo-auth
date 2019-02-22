package com.demo.auth.bean.domain;

import com.demo.auth.common.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserInfo extends BaseEntity {

    private String loginName; // 登录名
    private String userName; // 用户名称
    private String password; // 密码
    private String salt; // 密码时间戳
}
