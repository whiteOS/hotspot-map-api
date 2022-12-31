package com.entire.hotspotmap.system.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.entire.hotspotmap.system.main.entity.Role;
import com.entire.hotspotmap.system.main.param.RoleParam;
import com.entire.hotspotmap.system.main.service.RoleService;
import com.entire.hotspotmap.system.main.web.ApiResult;
import com.entire.hotspotmap.system.main.web.BaseController;
import com.entire.hotspotmap.system.main.web.PageParam;
import com.entire.hotspotmap.system.main.web.PageResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/system/role")
public class RoleController extends BaseController {
    @Resource
    private RoleService roleService;

    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/page")
    public ApiResult<PageResult<Role>> page(RoleParam param) {
        PageParam<Role, RoleParam> page = new PageParam<>(param);
        param.setOrder("role_id desc");
//        return success(roleService.page(page, page.getWrapper()));
        return success(roleService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping()
    public ApiResult<List<Role>> list(RoleParam param) {
        PageParam<Role, RoleParam> page = new PageParam<>(param);
        page.setDefaultOrder("role_id desc");
        return success(roleService.list(page.getOrderWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/{id}")
    public ApiResult<Role> get(@PathVariable("id") Integer id) {
        return success(roleService.getById(id));
    }

    @PreAuthorize("hasAuthority('sys:role:save')")
    @PostMapping()
    public ApiResult<?> save(@RequestBody Role role) {
        if (roleService.count(new LambdaQueryWrapper<Role>().eq(Role::getRoleName, role.getRoleName())) > 0) {
            return fail("角色名称已存在");
        }
        if (roleService.save(role)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:role:update')")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Role role) {
        if (role.getRoleName() != null && roleService.count(new LambdaQueryWrapper<Role>()
                .eq(Role::getRoleName, role.getRoleName())
                .ne(Role::getRoleId, role.getRoleId())) > 0) {
            return fail("角色名称已存在");
        }
        if (roleService.updateById(role)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:role:remove')")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (roleService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:role:remove')")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (roleService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }
}

