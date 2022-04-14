package com.re0.disco.api.controller;

import com.re0.disco.common.base.BaseController;
import com.re0.disco.domain.entity.Menu;
import com.re0.disco.domain.vo.response.MenuVO;
import com.re0.disco.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fangxi created by 2022/4/14
 */
@Api(tags = "菜单接口")
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController extends BaseController<Menu, MenuService> {

    @ApiOperation("根据当前登录用户构建菜单树")
    @GetMapping("/tree")
    public List<MenuVO> buildMenuTree() {
        return baseService.buildMenuTree();
    }
}
