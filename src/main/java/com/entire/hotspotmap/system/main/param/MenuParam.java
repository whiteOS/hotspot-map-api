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
public class MenuParam extends BaseParam {
    @QueryField(type = QueryType.EQ)
    private Integer menuId;

    @QueryField(type = QueryType.EQ)
    private Integer parentId;

    private String title;

    private String path;

    private String component;

    @QueryField(type = QueryType.EQ)
    private Integer menuType;

    private String authority;

    private String icon;
}
