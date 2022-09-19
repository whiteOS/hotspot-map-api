package com.entire.hotspotmap.system.main.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "登录参数")
public class LoginParam implements Serializable {
    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
