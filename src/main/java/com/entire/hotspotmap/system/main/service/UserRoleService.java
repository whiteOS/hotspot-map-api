package com.entire.hotspotmap.system.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entire.hotspotmap.system.main.entity.Role;
import com.entire.hotspotmap.system.main.entity.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {
    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return List<Role>
     */
    List<Role> listByUserId(Long userId);

    /**
     * 批量根据用户id查询角色
     *
     * @param userIds 用户id集合
     * @return List<RoleResult>
     */
    List<Role> listByUserIds(List<Long> userIds);
}

