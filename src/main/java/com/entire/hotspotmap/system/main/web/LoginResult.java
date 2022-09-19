package com.entire.hotspotmap.system.main.web;

import com.entire.hotspotmap.system.main.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "登录返回结果")
public class LoginResult{
    @ApiModelProperty("access_token")
    private String access_token;

    @ApiModelProperty("用户信息")
    private User user;
}
