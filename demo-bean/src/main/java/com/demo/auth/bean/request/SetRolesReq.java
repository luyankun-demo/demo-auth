package com.demo.auth.bean.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class SetRolesReq {

    private Integer userId;
    private List<Integer> roleIds;
}
