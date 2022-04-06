package com.re0.disco.common.annotation;

import java.lang.annotation.*;

/**
 * @author fangxi created by 2022/4/6
 * 用于标记匿名访问方法
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {
}
