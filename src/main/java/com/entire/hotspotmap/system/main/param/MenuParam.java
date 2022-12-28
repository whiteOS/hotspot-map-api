package com.entire.hotspotmap.system.main.param;

import com.entire.hotspotmap.system.annotation.QueryField;
import com.entire.hotspotmap.system.annotation.QueryType;
import com.entire.hotspotmap.system.main.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "菜单查询参数")
public class MenuParam extends BaseParam {
    @ApiModelProperty("菜单id")
    @QueryField(type = QueryType.EQ)
    private Integer menuId;

    @ApiModelProperty("父id, 0是顶级")
    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("菜单路由地址")
    private String path;

    @ApiModelProperty("菜单组件地址")
    private String component;

    @ApiModelProperty("菜单类型, 0菜单, 1按钮")
    @QueryField(type = QueryType.EQ)
    private Integer menuType;

    @ApiModelProperty("权限标识")
    private String authority;

    @ApiModelProperty("菜单图标")
    private String icon;
}
