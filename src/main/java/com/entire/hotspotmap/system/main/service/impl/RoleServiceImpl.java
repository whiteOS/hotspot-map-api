package com.entire.hotspotmap.system.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entire.hotspotmap.system.main.entity.Role;
import com.entire.hotspotmap.system.main.mapper.RoleMapper;
import com.entire.hotspotmap.system.main.param.RoleParam;
import com.entire.hotspotmap.system.main.service.RoleService;
import com.entire.hotspotmap.system.main.web.PageParam;
import com.entire.hotspotmap.system.main.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public PageResult<Role> pageRel(RoleParam param) {
        PageParam<Role, RoleParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<Role> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<Role> listRel(RoleParam param) {
        List<Role> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<Role, RoleParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }
}
