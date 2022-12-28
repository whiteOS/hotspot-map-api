package com.entire.hotspotmap.system.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    @TableId(type = IdType.AUTO, value = "user_role_id")
    private Integer userRoleId;

    private Long userId;

    private Integer roleId;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private String roleName;
}
