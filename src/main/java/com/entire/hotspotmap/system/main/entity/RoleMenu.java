package com.entire.hotspotmap.system.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class RoleMenu {
    @TableId(type = IdType.AUTO, value = "role_menu_id")
    private Integer roleMenuId;

    private Integer roleId;

    private Integer menuId;

    private Date createTime;

    private Date updateTime;
}
