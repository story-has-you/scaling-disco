package com.re0.disco.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author fangxi created by 2022/4/6
 * 用户角色关联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user_role")
public class UserRole extends BaseEntity {
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;
}
