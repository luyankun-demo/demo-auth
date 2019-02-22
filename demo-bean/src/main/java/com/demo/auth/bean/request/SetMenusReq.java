package com.demo.auth.bean.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class SetMenusReq {

    private Integer roleId;
    private List<Integer> menuIds;
}
