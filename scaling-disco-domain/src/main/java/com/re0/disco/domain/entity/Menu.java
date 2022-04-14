package com.re0.disco.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author fangxi created by 2022/4/6
 * 系统菜单
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @TableField(value = "`level`")
    private Integer level;

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
     * 链接地址
     */
    @TableField(value = "path")
    private String path;

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
     * 权限
     */
    @TableField(value = "permission")
    private String permission;
}
