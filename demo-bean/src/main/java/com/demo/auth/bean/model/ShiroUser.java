package com.demo.auth.bean.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ShiroUser implements Serializable {

    private String loginName; // 登录名称
    private String password; // 登录密码
    private String salt; // 密码时间戳
    private List<ShiroRole> shiroRoles; // 已配置角色集合
    private List<ShiroPermission> shiroPermissions; // 已配置菜单集合
}
