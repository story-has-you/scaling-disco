package com.re0.disco.api.controller;

import com.re0.disco.common.base.BaseController;
import com.re0.disco.domain.entity.User;
import com.re0.disco.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fangxi created by 2022/4/6
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, UserService> {

}
