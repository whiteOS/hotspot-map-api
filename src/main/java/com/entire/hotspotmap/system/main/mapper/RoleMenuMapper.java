package com.entire.hotspotmap.system.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entire.hotspotmap.system.main.entity.Menu;
import com.entire.hotspotmap.system.main.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    /**
     * 查询用户的菜单
     *
     * @param userId   用户id
     * @param menuType 菜单类型
     * @return List<Menu>
     */
    List<Menu> listMenuByUserId(@Param("userId") Long userId, @Param("menuType") Integer menuType);

    /**
     * 批量根据角色id查询菜单
     *
     * @param roleIds  角色id
     * @param menuType 菜单类型
     * @return List<Menu>
     */
    List<Menu> listMenuByRoleIds(@Param("roleIds") List<Integer> roleIds, @Param("menuType") Integer menuType);
}
