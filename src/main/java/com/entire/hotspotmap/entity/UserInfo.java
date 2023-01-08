package com.entire.hotspotmap.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Long userId;

    private String username;

    private String nickname;

    private String phone;

    private String email;

    private Date createTime;

    private Date lastLoginTime;
}
