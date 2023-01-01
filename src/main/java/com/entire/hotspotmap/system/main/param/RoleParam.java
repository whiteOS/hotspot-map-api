package com.entire.hotspotmap.system.main.param;

import com.entire.hotspotmap.system.annotation.QueryField;
import com.entire.hotspotmap.system.annotation.QueryType;
import com.entire.hotspotmap.system.main.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleParam extends BaseParam {
    @QueryField(type = QueryType.EQ)
    private Integer roleId;

    private String roleName;

    private String comments;

    private Integer authority;

    //    @TableLogic
    private Integer deleted;
}
