package com.re0.disco.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.re0.disco.common.base.BaseEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author fangxi created by 2022/4/6
 * 系统用户
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user")
public class User extends BaseEntity {
    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 1.男, 2.女
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 头像地址
     */
    @TableField(value = "avatar_name")
    private String avatarName;

    /**
     * 是否为admin账号
     */
    @TableField(value = "is_admin")
    private Boolean isAdmin;

    /**
     * 状态：1启用、0禁用
     */
    @TableField(value = "enabled")
    private Boolean enabled;

    /**
     * 修改密码的时间
     */
    @TableField(value = "pwd_reset_time")
    private LocalDateTime pwdResetTime;
}
