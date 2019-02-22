package com.demo.auth.bean.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 菜单权限
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ShiroPermission implements Serializable {

    private String menuName; // 菜单名称
    private String menuCode; // 菜单编码
    private String menuPath; // 菜单路径
    private Integer menuType; // 菜单类型: 1.菜单, 2.按钮
    private Integer parentId; // 父级ID
    private Integer sort; // 排序序号
    private Integer level; // 菜单层级
}
