package com.entire.hotspotmap.system.main.controller;

import com.entire.hotspotmap.system.config.ConfigProperties;
import com.entire.hotspotmap.system.main.entity.User;
import com.entire.hotspotmap.system.main.param.LoginParam;
import com.entire.hotspotmap.system.main.service.UserService;
import com.entire.hotspotmap.system.main.web.ApiResult;
import com.entire.hotspotmap.system.main.web.BaseController;
import com.entire.hotspotmap.system.main.web.LoginResult;
import com.entire.hotspotmap.system.security.JwtSubject;
import com.entire.hotspotmap.system.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class UserController extends BaseController {
    @Resource
    private ConfigProperties configProperties;
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ApiResult<LoginResult> login(@RequestBody LoginParam param) {
        String username = param.getUsername();
        User user = userService.getByUsername(username);
        if (user == null) {
            return fail("账号或密码错误", null);
        } else {
            if (!userService.comparePassword(user, param.getPassword())) {
                return fail("账号或密码错误", null);
            }
            if (!user.isAccountNonLocked()) {
                return fail("该账号被锁定",null);
            }
        }
        String access_token = JwtUtil.buildToken(new JwtSubject(username),
                configProperties.getTokenExpireTime(), configProperties.getTokenKey());
        return success("登录成功", new LoginResult(access_token, user));
    }
}
