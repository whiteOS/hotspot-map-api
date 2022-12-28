package com.entire.hotspotmap.system.main.web;

import com.entire.hotspotmap.system.main.entity.User;
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
