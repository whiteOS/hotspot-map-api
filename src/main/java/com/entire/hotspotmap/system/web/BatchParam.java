package com.entire.hotspotmap.system.web;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 批量修改通用参数
 *
 * @author EleAdmin
 * @since 2020-03-13 00:11:06
 */
@Data
public class BatchParam<T> {

    private List<Serializable> ids;

    private T data;

    /**
     * 通用批量修改方法
     *
     * @param service IService
     * @param idField id字段名称
     * @return boolean
     */
    public boolean update(IService<T> service, String idField) {
        if (this.data == null) {
            return false;
        }
        return service.update(this.data, new UpdateWrapper<T>().in(idField, this.ids));
    }

    /**
     * 通用批量修改方法
     *
     * @param service IService
     * @param idField id字段名称
     * @return boolean
     */
    public boolean update(IService<T> service, SFunction<T, ?> idField) {
        if (this.data == null) {
            return false;
        }
        return service.update(this.data, new LambdaUpdateWrapper<T>().in(idField, this.ids));
    }

}
