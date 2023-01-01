package com.entire.hotspotmap.system.main.controller;

import com.entire.hotspotmap.system.config.ConfigProperties;
import com.entire.hotspotmap.system.main.entity.Menu;
import com.entire.hotspotmap.system.main.entity.PersonalInfo;
import com.entire.hotspotmap.system.main.entity.User;
import com.entire.hotspotmap.system.main.param.LoginParam;
import com.entire.hotspotmap.system.main.service.UserService;
import com.entire.hotspotmap.system.main.service.RoleMenuService;
import com.entire.hotspotmap.system.main.web.ApiResult;
import com.entire.hotspotmap.system.main.web.BaseController;
import com.entire.hotspotmap.system.main.web.LoginResult;
import com.entire.hotspotmap.system.security.JwtUtil;
import com.entire.hotspotmap.system.utils.CommonUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController extends BaseController {
    @Resource
    private ConfigProperties configProperties;
    @Resource
    private UserService userService;
    @Resource
    private RoleMenuService roleMenuService;

    @PostMapping("/login")
    public ApiResult<LoginResult> login(@RequestBody LoginParam param) {
        String username = param.getUsername();
        User user = userService.getByUsername(username);
        if (user == null || !userService.comparePassword(user, param.getPassword())) {
            return fail("账号或密码错误", null);
        }
        if (!user.isAccountNonLocked()) {
            return fail("该账号被锁定", null);
        }
//        user.setLastLoginTime(new Date(System.currentTimeMillis()));
//        userService.updateUser(user);
        String access_token = JwtUtil.buildToken(username,
                configProperties.getTokenExpireTime(), configProperties.getTokenKey());
        return success("登录成功", new LoginResult(access_token, user));
    }

    @GetMapping("/auth/user")
    public ApiResult<PersonalInfo> userInfo() {
        PersonalInfo personalInfo = userService.selectPersonalInfo(getLoginUserId());
        return success(personalInfo);
    }

    @GetMapping("/auth/menu")
    public ApiResult<List<Menu>> userMenu() {
        List<Menu> menus = roleMenuService.listMenuByUserId(getLoginUserId(), 0);
        return success(CommonUtil.toTreeData(menus, 0, Menu::getParentId, Menu::getMenuId, Menu::setChildren));
    }
}
