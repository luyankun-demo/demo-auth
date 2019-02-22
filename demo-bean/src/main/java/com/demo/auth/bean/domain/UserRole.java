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
public class UserRole extends BaseEntity {

    private Long userId; // 用户ID
    private Long roleId; // 角色ID
}
