package com.entire.hotspotmap.system.main.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.entire.hotspotmap.system.main.entity.Role;
import com.entire.hotspotmap.system.main.param.RoleParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<RoleV>
     */
    List<Role> selectPageRel(@Param("page") IPage<Role> page,
                             @Param("param") RoleParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<Role> selectListRel(@Param("param") RoleParam param);
}
