package com.entire.hotspotmap.system.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entire.hotspotmap.system.main.entity.Role;
import com.entire.hotspotmap.system.main.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 批量添加用户角色
     *
     * @param userId  用户id
     * @param roleIds 角色id集合
     * @return int
     */
    List<Role> insertBatch(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return List<Role>
     */
    List<Role> selectByUserId(@Param("userId") Long userId);

    /**
     * 批量根据用户id查询角色
     *
     * @param userIds 用户id集合
     * @return List<RoleResult>
     */
    List<Role> selectByUserIds(@Param("userIds") List<Long> userIds);
}

