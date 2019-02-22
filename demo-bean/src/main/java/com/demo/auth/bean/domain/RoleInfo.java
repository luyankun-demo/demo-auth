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
public class RoleInfo extends BaseEntity {

    private String roleName; // 角色名称
    private String roleCode; // 角色编码
}
