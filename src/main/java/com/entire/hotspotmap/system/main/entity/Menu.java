package com.entire.hotspotmap.system.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "菜单")
public class Menu {
    @ApiModelProperty("菜单id")
    @TableId(type = IdType.AUTO, value = "menu_id")
    private Integer menuId;

    @ApiModelProperty("父id, 0是顶级")
    private Integer parentId;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("菜单路由地址")
    private String path;

    @ApiModelProperty("菜单组件地址")
    private String component;

    @ApiModelProperty("菜单类型, 0菜单, 1按钮")
    private Integer menuType;

    @ApiModelProperty("排序号")
    private Integer arrangement;

    @ApiModelProperty("权限标识")
    private String authority;

    @ApiModelProperty("打开位置")
    private String target;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("角色权限树选中状态")
    @TableField(exist = false)
    private Boolean checked;
}
