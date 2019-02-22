package com.demo.auth.bean.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ShiroRole implements Serializable {

    private String roleName; // 角色名称
    private String roleCode; // 角色编码
}
