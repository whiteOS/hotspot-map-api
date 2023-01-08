package com.entire.hotspotmap.controller;

import com.entire.hotspotmap.entity.User;
import com.entire.hotspotmap.param.UserParam;
import com.entire.hotspotmap.service.UserService;
import com.entire.hotspotmap.system.web.ApiResult;
import com.entire.hotspotmap.system.web.BaseController;
import com.entire.hotspotmap.system.web.PageResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/system/user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

//    @GetMapping("/page")
//    public ApiResult<PageResult<User>> page(UserParam param) {
//        return success(userService.pageRel(param));
//    }
//
//    @GetMapping()
//    public ApiResult<List<User>> list(UserParam param) {
//        return success(userService.listRel(param));
//    }
//
//    @GetMapping("/{id}")
//    public ApiResult<User> get(@PathVariable("id") Long id) {
//        return success(userService.getByIdRel(id));
//    }
//
//    @GetMapping("/listByRoleId")
//    public ApiResult<List<User>> listByRoleId(UserParam param) {
//        return success(userService.selectByRoleId(param.getRoleId()));
//    }

    @PostMapping()
    public ApiResult<?> add(@RequestBody User user) {
        if (userService.saveUser(user)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PutMapping()
    public ApiResult<?> update(@RequestBody User user) {
        user.setUsername(null);
        user.setPassword(null);
        if (userService.updateUser(user)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

//    @DeleteMapping("/{id}")
//    public ApiResult<?> remove(@PathVariable("id") Integer id) {
//        if (userService.removeById(id)) {
//            return success("删除成功");
//        }
//        return fail("删除失败");
//    }

}
