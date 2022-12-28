package com.entire.hotspotmap.system.main.web;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询返回结果
 *
 * @author EleAdmin
 * @since 2017-06-10 10:10:02
 */
public class PageResult<T> implements Serializable {

    private List<T> list;

    private Long count;

    public PageResult() {
    }

    public PageResult(List<T> list) {
        this(list, null);
    }

    public PageResult(List<T> list, Long count) {
        setList(list);
        setCount(count);
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
