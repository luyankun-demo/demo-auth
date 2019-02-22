package com.demo.auth.bean.model;

import com.demo.auth.bean.domain.MenuInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
public class MenuGradeInfo extends MenuInfo {

    private List<MenuGradeInfo> children;
}
