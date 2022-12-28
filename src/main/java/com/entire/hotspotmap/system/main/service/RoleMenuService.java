package com.entire.hotspotmap.system.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entire.hotspotmap.system.main.entity.Menu;
import com.entire.hotspotmap.system.main.entity.RoleMenu;

import java.util.List;

public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 查询用户对应的菜单
     *
     * @param userId   用户id
     * @param menuType 菜单类型
     * @return List<Menu>
     */
    List<Menu> listMenuByUserId(Long userId, Integer menuType);

    /**
     * 批量查询用户对应的菜单
     *
     * @param roleIds  角色id集合
     * @param menuType 菜单类型
     * @return List<Menu>
     */
    List<Menu> listMenuByRoleIds(List<Integer> roleIds, Integer menuType);

}
