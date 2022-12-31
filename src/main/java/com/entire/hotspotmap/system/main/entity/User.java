package com.entire.hotspotmap.system.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

@Data
public class User implements UserDetails {
    @TableId(type = IdType.AUTO, value = "user_id")
    private Long userId;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String email;

    private Date createTime;

    private Date updateTime;

    private Date expireTime;

    private Date lastLoginTime;

    @TableLogic
    private Integer deleted;

    private Integer status;

    @TableField(exist = false)
    private List<Role> roles;

    @TableField(exist = false)
    private List<Menu> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status==1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.deleted==0;
    }
}
