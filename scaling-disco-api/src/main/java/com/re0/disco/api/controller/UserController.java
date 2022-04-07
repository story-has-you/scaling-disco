package com.re0.disco.api.controller;

import com.re0.disco.api.config.security.AuthUser;
import com.re0.disco.api.config.security.TokenProvider;
import com.re0.disco.common.annotation.AnonymousAccess;
import com.re0.disco.common.base.BaseController;
import com.re0.disco.common.utils.BeanUtils;
import com.re0.disco.common.utils.SecurityUtils;
import com.re0.disco.domain.entity.User;
import com.re0.disco.domain.vo.request.LoginUserVO;
import com.re0.disco.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author fangxi created by 2022/4/6
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController extends BaseController<User, UserService> {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @PostMapping("/create")
    @ApiOperation("新增用户")
    public Long create(@RequestBody @Validated LoginUserVO loginUserVO) {
        String password = passwordEncoder.encode(loginUserVO.getPassword());
        loginUserVO.setPassword(password);
        User user = BeanUtils.copyProperties(loginUserVO, User.class);
        return super.save(user);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    @AnonymousAccess
    public String login(@RequestBody @Validated LoginUserVO loginUserVO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserVO.getUsername(), loginUserVO.getPassword());
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return tokenProvider.createToken(authenticate);
    }


    @ApiOperation("获取当前登录用户")
    @GetMapping("/current")
    public AuthUser currentLoginUser() {
        return (AuthUser) SecurityUtils.getCurrentUser();
    }
}
