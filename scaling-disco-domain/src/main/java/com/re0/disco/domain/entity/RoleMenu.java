package com.re0.disco.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.re0.disco.common.base.BaseEntity;
import lombok.*;

/**
 * @author fangxi created by 2022/4/6
 * 角色菜单关联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role_menu")
public class RoleMenu extends BaseEntity {
    /**
     * 菜单ID
     */
    @TableField(value = "menu_id")
    private Long menuId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;
}
