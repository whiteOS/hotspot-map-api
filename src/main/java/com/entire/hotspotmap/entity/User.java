package com.entire.hotspotmap.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
@TableName("user_details")
public class User implements UserDetails {
    @TableId(type = IdType.AUTO, value = "user_id")
    private Long userId;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String email;

    private Date createTime;

    private Date lastLoginTime;

//    @TableLogic
//    private Integer deleted;
//
//    private Integer status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return  true;
//        return this.status==1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return  true;
//        return this.deleted==0;
    }
}
