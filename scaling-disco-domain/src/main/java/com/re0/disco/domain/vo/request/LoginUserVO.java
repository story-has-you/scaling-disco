package com.re0.disco.domain.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author fangxi created by 2022/4/7
 */
@Data
public class LoginUserVO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String code;

}
