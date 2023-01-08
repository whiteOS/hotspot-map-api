package com.entire.hotspotmap.controller;

import com.entire.hotspotmap.param.LoginParam;
import com.entire.hotspotmap.system.config.ConfigProperties;
import com.entire.hotspotmap.entity.UserInfo;
import com.entire.hotspotmap.entity.User;
import com.entire.hotspotmap.service.UserService;
import com.entire.hotspotmap.system.web.ApiResult;
import com.entire.hotspotmap.system.web.BaseController;
import com.entire.hotspotmap.system.web.LoginResult;
import com.entire.hotspotmap.system.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class MainController extends BaseController {
    @Resource
    private ConfigProperties configProperties;
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ApiResult<LoginResult> login(@RequestBody LoginParam param) {
        String username = param.getUsername();
        User user = userService.getByUsername(username);
        if (user == null || !userService.comparePassword(user, param.getPassword())) {
            return fail("账号或密码错误", null);
        }
//        if (!user.isAccountNonLocked()) {
//            return fail("该账号被锁定", null);
//        }
//        user.setLastLoginTime(new Date(System.currentTimeMillis()));
//        userService.updateUser(user);
        String access_token = JwtUtil.buildToken(username,
                configProperties.getTokenExpireTime(), configProperties.getTokenKey());
        return success("登录成功", new LoginResult(access_token, user));
    }

    @GetMapping("/auth/user")
    public ApiResult<UserInfo> userInfo() {
        UserInfo userInfo = userService.getUserInfo(getLoginUserId());
        return success(userInfo);
    }
}