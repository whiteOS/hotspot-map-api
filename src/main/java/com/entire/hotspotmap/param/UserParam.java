package com.entire.hotspotmap.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.entire.hotspotmap.system.annotation.QueryField;
import com.entire.hotspotmap.system.annotation.QueryType;
import com.entire.hotspotmap.system.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserParam extends BaseParam {
    @QueryField(type = QueryType.EQ)
    private Long userId;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String email;

//    //    @TableLogic
//    private Integer deleted;
//
//    private Integer status;
//
//    @TableField(exist = false)
//    private Integer roleId;
}
