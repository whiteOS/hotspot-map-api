package com.entire.hotspotmap.system.main.param;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(description = "用户查询参数")
public class UserParam extends BaseParam {
    @ApiModelProperty("用户id")
    @QueryField(type = QueryType.EQ)
    private Long userId;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("是否删除, 0否, 1是")
//    @TableLogic
    private Integer deleted;

    @ApiModelProperty("0：已被冻结, 1：状态正常")
    private Integer status;

    @ApiModelProperty("角色id")
    @TableField(exist = false)
    private Integer roleId;
}
