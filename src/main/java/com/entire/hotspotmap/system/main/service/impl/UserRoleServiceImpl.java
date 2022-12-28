package com.entire.hotspotmap.system.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entire.hotspotmap.system.main.entity.Role;
import com.entire.hotspotmap.system.main.entity.UserRole;
import com.entire.hotspotmap.system.main.mapper.UserRoleMapper;
import com.entire.hotspotmap.system.main.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    public List<Role> listByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<Role> listByUserIds(List<Long> userIds) {
        return baseMapper.selectByUserIds(userIds);
    }
}
