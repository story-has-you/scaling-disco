package com.re0.disco.api.controller;

import com.re0.disco.common.annotation.AnonymousAccess;
import com.re0.disco.common.base.BaseController;
import com.re0.disco.domain.entity.User;
import com.re0.disco.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fangxi created by 2022/4/6
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController<User, UserService> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    @ApiOperation("新增用户")
    @AnonymousAccess
    public Long create(@RequestBody User entity) {
        String password = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(password);
        return super.save(entity);
    }
}
