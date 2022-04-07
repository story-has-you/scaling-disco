package com.re0.disco.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.re0.disco.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fangxi created by 2022/4/6
 * 系统菜单
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_menu")
public class Menu extends BaseEntity {
    /**
     * 上级菜单ID
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 子菜单数目
     */
    @TableField(value = "sub_count")
    private Integer subCount;

    /**
     * 菜单类型
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 菜单标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 组件名称
     */
    @TableField(value = "component_name")
    private String componentName;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 链接地址
     */
    @TableField(value = "`path`")
    private String path;

    /**
     * 权限
     */
    @TableField(value = "permission")
    private String permission;
}
