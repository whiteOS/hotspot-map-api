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
@ApiModel(description = "角色查询参数")
public class RoleParam extends BaseParam {
    @ApiModelProperty("角色id")
    @QueryField(type = QueryType.EQ)
    private Integer roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty("显示有权限的菜单")
    private Integer authority;

    @ApiModelProperty("是否删除, 0否, 1是")
//    @TableLogic
    private Integer deleted;
}
