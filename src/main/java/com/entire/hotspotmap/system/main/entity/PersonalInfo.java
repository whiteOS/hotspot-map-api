package com.entire.hotspotmap.system.main.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PersonalInfo {
    private Long userId;

    private String username;

    private String nickname;

    private String phone;

    private String email;

    private Date createTime;

    private Date expireTime;

    private Date lastLoginTime;
}
