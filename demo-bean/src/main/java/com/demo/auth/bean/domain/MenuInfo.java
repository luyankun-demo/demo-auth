package com.demo.auth.bean.domain;

import com.demo.auth.common.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
public class MenuInfo extends BaseEntity {

    private String menuName; // 菜单名称
    private String menuCode; // 菜单编码
    private String menuPath; // 菜单路径
    private Integer menuType; // 菜单类型: 1.菜单, 2.按钮
    private Integer parentId; // 父级ID
    private Integer sort; // 排序序号
    private Integer level; // 菜单层级

}
