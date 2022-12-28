package com.entire.hotspotmap.system.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entire.hotspotmap.system.main.entity.Role;
import com.entire.hotspotmap.system.main.param.RoleParam;
import com.entire.hotspotmap.system.main.web.PageResult;

import java.util.List;

public interface RoleService extends IService<Role> {
    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<RoleV>
     */
    PageResult<Role> pageRel(RoleParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<RoleV>
     */
    List<Role> listRel(RoleParam param);
}
