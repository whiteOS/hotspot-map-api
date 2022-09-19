package com.entire.hotspotmap.system.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "角色权限")
public class RoleMenu {
    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO, value = "main_id")
    private Integer mainId;

    @ApiModelProperty("角色id")
    private Integer roleId;

    @ApiModelProperty("菜单id")
    private Integer menuId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;
}
