package com.entire.hotspotmap.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entire.hotspotmap.entity.UserInfo;
import com.entire.hotspotmap.entity.User;
import com.entire.hotspotmap.mapper.UserMapper;
import com.entire.hotspotmap.param.UserParam;
import com.entire.hotspotmap.service.UserService;
import com.entire.hotspotmap.system.exception.BusinessException;
import com.entire.hotspotmap.system.web.PageParam;
import com.entire.hotspotmap.system.web.PageResult;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

//    @Override
//    public PageResult<User> pageRel(UserParam param) {
//        PageParam<User, UserParam> page = new PageParam<>(param);
//        page.setDefaultOrder("create_time desc");
//        List<User> list = baseMapper.selectPageRel(page, param);
//        // 查询用户的角色
//        selectUserRoles(list);
//        return new PageResult<>(list, page.getTotal());
//    }

//    @Override
//    public List<User> listRel(UserParam param) {
//        List<User> list = baseMapper.selectListRel(param);
//        // 查询用户的角色
//        selectUserRoles(list);
//        // 排序
//        PageParam<User, UserParam> page = new PageParam<>(param);
//        page.setDefaultOrder("create_time desc");
//        return page.sortRecords(list);
//    }

//    @Override
//    public User getByIdRel(Long userId) {
//        UserParam param = new UserParam();
//        param.setUserId(userId);
//        User user = param.getOne(baseMapper.selectListRel(param));
//        if (user != null) {
//            user.setRoles(userRoleService.listByUserId(user.getUserId()));
//            user.setAuthorities(roleMenuService.listMenuByUserId(user.getUserId(), null));
//        }
//        return user;
//    }

//    登录
    @Override
    public User getByUsername(String username) {
        //        if (user != null) {
//            user.setRoles(userRoleService.listByUserId(user.getUserId()));
//            user.setAuthorities(roleMenuService.listMenuByUserId(user.getUserId(), null));
//        }
        return baseMapper.selectByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }

//    注册
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    @Override
    public boolean saveUser(User user) {
        if (user.getUsername() != null && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername())) > 0) {
            throw new BusinessException("账号已存在");
        }
        if (user.getPhone() != null && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, user.getPhone())) > 0) {
            throw new BusinessException("手机号已存在");
        }
        if (user.getEmail() != null  && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, user.getEmail())) > 0) {
            throw new BusinessException("邮箱已存在");
        }
        //        List<UserRole> userRoleList = new ArrayList<>();
//        if (result && user.getRoles() != null && user.getRoles().size() > 0) {
//            List<Integer> roleIds = user.getRoles().stream().map(Role::getRoleId).toList();
//
//            for (Integer roleId:roleIds){
//                UserRole userRole = new UserRole();
//                userRole.setRoleId(roleId);
//                userRole.setUserId(user.getUserId());
//                userRoleList.add(userRole);
//            }
//
//            if (!userRoleService.saveBatch(userRoleList)) {
//                throw new BusinessException("用户角色添加失败");
//            }
//        }
        return baseMapper.insert(user) > 0;
    }

//    修改个人信息
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(User user) {
        if (user.getUsername() != null  && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername())
                .ne(User::getUserId, user.getUserId())) > 0) {
            throw new BusinessException("账号已存在");
        }
        if (user.getPhone() != null  && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, user.getPhone())
                .ne(User::getUserId, user.getUserId())) > 0) {
            throw new BusinessException("手机号已存在");
        }
        if (user.getEmail() != null  && baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, user.getEmail())
                .ne(User::getUserId, user.getUserId())) > 0) {
            throw new BusinessException("邮箱已存在");
        }
        //        if (result && user.getRoles() != null && user.getRoles().size() > 0) {
//            userRoleService.remove(new LambdaUpdateWrapper<UserRole>().eq(UserRole::getUserId, user.getUserId()));
//            List<Integer> roleIds = user.getRoles().stream().map(Role::getRoleId).toList();
//
//            List<UserRole> userRoleList = new ArrayList<>();
//            for (Integer roleId:roleIds){
//                UserRole userRole = new UserRole();
//                userRole.setRoleId(roleId);
//                userRole.setUserId(user.getUserId());
//                userRoleList.add(userRole);
//            }
//            if (!userRoleService.saveBatch(userRoleList)) {
//                throw new BusinessException("用户角色添加失败");
//            }
//        }
        return baseMapper.updateById(user) > 0;
    }

//    修改密码
    @Override
    public boolean comparePassword(User user, String inputPassword) {
        return Objects.equals(inputPassword, user.getPassword());
    }

//    @Override
//    public List<User> selectByRoleId(Integer roleId) {
//        return baseMapper.selectByRoleId(roleId);
//    }

    @Override
    public UserInfo getUserInfo(Long userId) {
        return baseMapper.getUserInfo(userId);
    }

//    /**
//     * 批量查询用户的角色
//     *
//     * @param users 用户集合
//     */
//    private void selectUserRoles(List<User> users) {
//        if (users != null && users.size() > 0) {
//            List<Long> userIds = users.stream().map(User::getUserId).collect(Collectors.toList());
//            List<Role> userRoles = userRoleService.listByUserIds(userIds);
//            for (User user : users) {
//                List<Role> roles = userRoles.stream().filter(d -> user.getUserId().equals(d.getUserId()))
//                        .collect(Collectors.toList());
//                user.setRoles(roles);
//            }
//        }
//    }
}
