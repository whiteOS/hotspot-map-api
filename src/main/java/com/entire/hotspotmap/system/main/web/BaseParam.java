package com.entire.hotspotmap.system.main.web;

import com.baomidou.mybatisplus.annotation.TableField;
import com.entire.hotspotmap.system.utils.CommonUtil;
import com.entire.hotspotmap.system.annotation.QueryField;
import com.entire.hotspotmap.system.annotation.QueryType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseParam implements Serializable {

    @TableField(exist = false)
    private Long page;

    @TableField(exist = false)
    private Long limit;

    @TableField(exist = false)
    private String sort;

    @TableField(exist = false)
    private String order;

    @QueryField(value = "create_time", type = QueryType.GE)
    @TableField(exist = false)
    private String createTimeStart;

    @QueryField(value = "create_time", type = QueryType.LE)
    @TableField(exist = false)
    private String createTimeEnd;

    @QueryField(value = "update_time", type = QueryType.GE)
    @TableField(exist = false)
    private String updateTimeStart;

    @QueryField(value = "update_time", type = QueryType.LE)
    @TableField(exist = false)
    private String updateTimeEnd;

    /**
     * 获取集合中的第一条数据
     *
     * @param records 集合
     * @return 第一条数据
     */
    public <T> T getOne(List<T> records) {
        return CommonUtil.listGetOne(records);
    }

}
