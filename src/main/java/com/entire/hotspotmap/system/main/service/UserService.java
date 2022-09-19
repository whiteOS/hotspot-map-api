package com.entire.hotspotmap.system.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entire.hotspotmap.system.main.entity.User;
import com.entire.hotspotmap.system.main.param.UserParam;
import com.entire.hotspotmap.system.main.web.PageResult;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends IService<User>, UserDetailsService {
    /**
     * 关联分页查询用户
     *
     * @param param 查询参数
     * @return PageResult<User>
     */
    PageResult<User> pageRel(UserParam param);

    /**
     * 关联查询全部用户
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<User> listRel(UserParam param);

    /**
     * 根据id查询用户
     *
     * @param userId 用户id
     * @return User
     */
    User getByIdRel(Long userId);

    /**
     * 根据账号查询用户
     *
     * @param username 账号
     * @return User
     */
    User getByUsername(String username);

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return boolean
     */
    boolean saveUser(User user);

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return boolean
     */
    boolean updateUser(User user);

    /**
     * 比较用户密码
     *
     * @param user    用户对象
     * @param inputPassword 用户输入的密码
     * @return boolean
     */
    boolean comparePassword(User user, String inputPassword);

    /**
     * 根据角色查询用户
     *
     * @param roleId
     * @return List<User>
     */
    List<User> selectByRoleId(Integer roleId);
}
