package com.entire.hotspotmap.system.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

@Data
public class Menu implements GrantedAuthority {
    @TableId(type = IdType.AUTO, value = "menu_id")
    private Integer menuId;

    private Integer parentId;

    private String title;

    private String path;

    private String component;

    private Integer menuType;

    private Integer arrangement;

    private String authority;

    private String target;

    private String icon;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private Boolean checked;
}
