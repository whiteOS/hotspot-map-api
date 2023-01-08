package com.entire.hotspotmap.system.web;

import com.entire.hotspotmap.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult{
    private String access_token;

    private User user;
}
