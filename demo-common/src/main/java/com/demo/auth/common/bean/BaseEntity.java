package com.demo.auth.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class BaseEntity {

    protected Integer id; // 主键
    protected Integer deleted; // 是否删除:1.正常, 2.删除
    protected Integer disabled; // 是否禁用:1.正常, 2.禁用
    protected String remark; // 备注
    protected Date createDate; // 创建时间
    protected Date updateDate; // 最后一次修改时间
}
