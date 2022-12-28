package com.entire.hotspotmap.system.main.controller;

import com.entire.hotspotmap.system.main.entity.User;
import com.entire.hotspotmap.system.main.param.UserParam;
import com.entire.hotspotmap.system.main.service.UserService;
import com.entire.hotspotmap.system.main.web.ApiResult;
import com.entire.hotspotmap.system.main.web.BaseController;
import com.entire.hotspotmap.system.main.web.PageResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/system/user")
public class UserManageController extends BaseController {
    @Resource
    private UserService userService;

    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/page")
    public ApiResult<PageResult<User>> page(UserParam param) {
        return success(userService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping()
    public ApiResult<List<User>> list(UserParam param) {
        return success(userService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/{id}")
    public ApiResult<User> get(@PathVariable("id") Long id) {
        return success(userService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/listByRoleId")
    public ApiResult<List<User>> listByRoleId(UserParam param) {
        return success(userService.selectByRoleId(param.getRoleId()));
    }

    @PreAuthorize("hasAuthority('sys:user:save')")
    @PostMapping()
    public ApiResult<?> add(@RequestBody User user) {
        user.setStatus(0);
        if (userService.saveUser(user)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:user:update')")
    @PutMapping()
    public ApiResult<?> update(@RequestBody User user) {
        user.setStatus(null);
        user.setUsername(null);
        user.setPassword(null);
        if (userService.updateUser(user)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:user:remove')")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (userService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
