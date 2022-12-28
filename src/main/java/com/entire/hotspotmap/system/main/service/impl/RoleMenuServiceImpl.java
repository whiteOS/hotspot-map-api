package com.entire.hotspotmap.system.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entire.hotspotmap.system.main.entity.Menu;
import com.entire.hotspotmap.system.main.entity.RoleMenu;
import com.entire.hotspotmap.system.main.mapper.RoleMenuMapper;
import com.entire.hotspotmap.system.main.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Override
    public List<Menu> listMenuByUserId(Long userId, Integer menuType) {
        return baseMapper.listMenuByUserId(userId, menuType);
    }

    @Override
    public List<Menu> listMenuByRoleIds(List<Integer> roleIds, Integer menuType) {
        return baseMapper.listMenuByRoleIds(roleIds, menuType);
    }
}
