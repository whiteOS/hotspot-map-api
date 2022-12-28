package com.entire.hotspotmap.system.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface QueryField {

    // 字段名称
    String value() default "";

    // 查询方式
    QueryType type() default QueryType.LIKE;

}
