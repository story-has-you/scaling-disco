package com.re0.disco.common.components;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * The type Default value meta object handler.
 *
 * @author fangxi created by 2020/6/17
 */
public class DefaultValueMetaObjectHandler implements MetaObjectHandler {

    /**
     * 通用字段：创建时间
     */
    private static final String CREATE_BY = "createBy";

    /**
     * 通用字段：更新时间
     */
    private static final String UPDATE_BY = "updateBy";


    @Override
    public void insertFill(MetaObject metaObject) {
        // 判断是否有相关字段
        boolean hasCreateBy = metaObject.hasSetter(CREATE_BY);
        boolean hasUpdateBy = metaObject.hasSetter(UPDATE_BY);
        String username = "";
        // 有字段则自动填充
        if (hasCreateBy) {
            strictInsertFill(metaObject, CREATE_BY, String.class, username);
        }
        if (hasUpdateBy) {
            strictInsertFill(metaObject, UPDATE_BY, String.class, username);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName(UPDATE_BY, metaObject);
        // 没有自定义值时才更新字段
        if (val == null) {
            String username = "";
            strictUpdateFill(metaObject, UPDATE_BY, String.class, username);
        }
    }
}
