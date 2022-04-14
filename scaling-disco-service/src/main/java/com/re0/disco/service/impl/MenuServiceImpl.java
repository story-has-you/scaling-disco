package com.re0.disco.service.impl;

import com.google.common.collect.Lists;
import com.re0.disco.common.base.BaseServiceImpl;
import com.re0.disco.common.utils.CollectionUtils;
import com.re0.disco.domain.entity.Menu;
import com.re0.disco.domain.entity.Role;
import com.re0.disco.domain.model.AuthUser;
import com.re0.disco.domain.vo.response.MenuVO;
import com.re0.disco.mapper.MenuMapper;
import com.re0.disco.service.MenuService;
import com.re0.disco.service.RoleMenuService;
import com.re0.disco.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author fangxi created by 2022/4/6
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {

    private final RoleService roleService;
    private final RoleMenuService roleMenuService;

    @Override
    public List<MenuVO> buildMenuTree() {
        // 所有层级的菜单已经查询
        List<Menu> menus = this.selectByCurrentUser();
        if (CollectionUtils.isEmpty(menus)) {
            return null;
        }
        // 构建菜单树
        List<MenuVO> menuVOList = Lists.newArrayList();
        List<Menu> rootMenuList = menus.stream().filter(menu -> Objects.isNull(menu.getPid())).collect(Collectors.toList());
        rootMenuList.stream().map(MenuVO::new).peek(menuVO -> {
            List<MenuVO> routes = this.buildMenuTree(menus, menuVO);
            menuVO.setRoutes(routes);
        }).forEach(menuVOList::add);
        return menuVOList;
    }

    @Override
    public List<Menu> selectByCurrentUser(AuthUser authUser) {
        List<Role> roles = roleService.selectByCurrentUser();
        List<Long> menuIds = roleMenuService.selectMenuIdsByRoleIds(CollectionUtils.map(roles, Role::getId));
        return lambdaQuery().in(Menu::getId, menuIds).orderByAsc(Menu::getSort).list();
    }

    private List<MenuVO> buildMenuTree(List<Menu> menus, MenuVO current) {
        List<Menu> menuList = menus.stream().filter(menu -> Objects.equals(menu.getPid(), current.getId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(menuList)) {
            return Collections.emptyList();
        }
        return menuList.stream().map(MenuVO::new).peek(menuVO -> {
            List<MenuVO> routes = this.buildMenuTree(menus, menuVO);
            menuVO.setRoutes(routes);
        }).collect(Collectors.toList());
    }


}
