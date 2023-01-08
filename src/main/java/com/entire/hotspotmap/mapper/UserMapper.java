package com.entire.hotspotmap.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entire.hotspotmap.entity.UserInfo;
import com.entire.hotspotmap.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
//    /**
//     * 分页查询
//     *
//     * @param page  分页对象
//     * @param param 查询参数
//     * @return List<User>
//     */
//    List<User> selectPageRel(@Param("page") IPage<User> page,
//                             @Param("param") UserParam param);
//
//    /**
//     * 查询全部
//     *
//     * @param param 查询参数
//     * @return List<User>
//     */
//    List<User> selectListRel(@Param("param") UserParam param);

    /**
     * 根据账号查询，登陆用
     *
     * @param username 账号
     * @return User
     */
    User selectByUsername(@Param("username") String username);

//    /**
//     * 根据角色查询
//     *
//     * @param roleId 角色编号
//     * @return User
//     */
//    List<User> selectByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据ID查询个人信息
     *
     * @param userId 用户ID
     * @return UserInfo
     */
    UserInfo getUserInfo(@Param("userId") Long userId);
}
