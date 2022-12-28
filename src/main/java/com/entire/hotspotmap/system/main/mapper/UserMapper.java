package com.entire.hotspotmap.system.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.entire.hotspotmap.system.main.entity.User;
import com.entire.hotspotmap.system.main.param.UserParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<User>
     */
    List<User> selectPageRel(@Param("page") IPage<User> page,
                             @Param("param") UserParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<User> selectListRel(@Param("param") UserParam param);

    /**
     * 根据账号查询
     *
     * @param username 账号
     * @return User
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 根据角色查询
     *
     * @param roleId 角色编号
     * @return User
     */
    List<User> selectByRoleId(@Param("roleId") Integer roleId);
}
