package com.entire.hotspotmap.system.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

@ApiModel(description = "用户")
@Data
public class User implements UserDetails {
    @ApiModelProperty("用户id")
    @TableId(type = IdType.AUTO, value = "user_id")
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

    @ApiModelProperty("账户注册时间")
    private Date createTime;

    @ApiModelProperty("账户修改时间")
    private Date updateTime;

    @ApiModelProperty("账户过期时间")
    private Date expireTime;

    @ApiModelProperty("上次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty("是否删除, 0否, 1是")
//    @TableLogic
    private Integer deleted;

    @ApiModelProperty("0：已被冻结, 1：状态正常")
    private Integer status;

    @ApiModelProperty("角色列表")
    @TableField(exist = false)
    private List<Role> roles;

    @ApiModelProperty("权限列表")
    @TableField(exist = false)
    private List<Menu> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return this.expireTime.after(this.createTime);
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status==1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.expireTime.after(this.createTime);
    }

    @Override
    public boolean isEnabled() {
        return this.deleted==0;
    }
}
