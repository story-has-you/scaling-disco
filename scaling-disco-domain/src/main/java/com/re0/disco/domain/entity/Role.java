package com.re0.disco.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.re0.disco.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fangxi created by 2022/4/6
 */

/**
 * 角色表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role")
public class Role extends BaseEntity {
    /**
     * 名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 角色级别, 越小越高
     */
    @TableField(value = "`level`")
    private Integer level;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;
}
