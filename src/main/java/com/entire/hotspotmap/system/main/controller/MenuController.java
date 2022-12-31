package com.entire.hotspotmap.system.main.controller;

import com.entire.hotspotmap.system.main.entity.Menu;
import com.entire.hotspotmap.system.main.param.MenuParam;
import com.entire.hotspotmap.system.main.service.MenuService;
import com.entire.hotspotmap.system.main.web.ApiResult;
import com.entire.hotspotmap.system.main.web.BaseController;
import com.entire.hotspotmap.system.main.web.PageParam;
import com.entire.hotspotmap.system.main.web.PageResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/system/menu")
public class MenuController extends BaseController {
    @Resource
    private MenuService menuService;

    @PreAuthorize("hasAuthority('sys:menu:list')")
    @GetMapping("/page")
    public ApiResult<PageResult<Menu>> page(MenuParam param) {
        PageParam<Menu, MenuParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(menuService.page(page, page.getWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:menu:list')")
    @GetMapping()
    public ApiResult<List<Menu>> list(MenuParam param) {
        PageParam<Menu, MenuParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(menuService.list(page.getOrderWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:menu:list')")
    @GetMapping("/{id}")
    public ApiResult<Menu> get(@PathVariable("id") Integer id) {
        return success(menuService.getById(id));
    }

    @PreAuthorize("hasAuthority('sys:menu:save')")
    @PostMapping()
    public ApiResult<?> add(@RequestBody Menu menu) {
        if (menuService.save(menu)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:update')")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Menu menu) {
        if (menuService.updateById(menu)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:remove')")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (menuService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:remove')")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (menuService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }
}
