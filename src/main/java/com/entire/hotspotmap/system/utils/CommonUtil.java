package com.entire.hotspotmap.system.utils;

import cn.hutool.core.util.ObjectUtil;
import com.entire.hotspotmap.system.Constants;
import com.entire.hotspotmap.system.web.ApiResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class CommonUtil {
    /**
     * 获取集合中的第一条数据
     *
     * @param records 集合
     * @return 第一条数据
     */
    public static <T> T listGetOne(List<T> records) {
        return records == null || records.size() == 0 ? null : records.get(0);
    }

    /**
     * 支持跨域
     *
     * @param response HttpServletResponse
     */
    public static void addCrossHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", Constants.TOKEN_HEADER_NAME);
    }

    /**
     * 输出错误信息
     *
     * @param response HttpServletResponse
     * @param code     错误码
     * @param message  提示信息
     * @param error    错误信息
     */
    public static void responseError(HttpServletResponse response, Integer code, String message, String error) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSONUtil.toJSONString(new ApiResult<>(code, message, null, error)));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * List转为树形结构
     *
     * @param data           List
     * @param parentId       顶级的parentId
     * @param parentIdMapper 获取parentId的Function
     * @param idMapper       获取id的Function
     * @param consumer       赋值children的Consumer
     * @param <T>            数据的类型
     * @param <R>            parentId的类型
     * @return List<T>
     */
    public static <T, R> List<T> toTreeData(List<T> data, R parentId,
                                            Function<? super T, ? extends R> parentIdMapper,
                                            Function<? super T, ? extends R> idMapper,
                                            BiConsumer<T, List<T>> consumer) {
        List<T> result = new ArrayList<>();
        for (T d : data) {
            R dParentId = parentIdMapper.apply(d);
            if (ObjectUtil.equals(parentId, dParentId)) {
                R dId = idMapper.apply(d);
                List<T> children = toTreeData(data, dId, parentIdMapper, idMapper, consumer);
                consumer.accept(d, children);
                result.add(d);
            }
        }
        return result;
    }
}
