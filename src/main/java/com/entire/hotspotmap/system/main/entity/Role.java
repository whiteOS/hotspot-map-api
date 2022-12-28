package com.entire.hotspotmap.system.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Role {
    @TableId(type = IdType.AUTO, value = "role_id")
    private Integer roleId;

    private String roleName;

    private String comments;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private Long userId;
}
