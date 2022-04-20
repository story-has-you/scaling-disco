package com.re0.disco.api.service;

import com.re0.disco.api.ScalingDiscoApplicationTest;
import com.re0.disco.common.utils.CollectionUtils;
import com.re0.disco.domain.entity.Menu;
import com.re0.disco.domain.entity.Role;
import com.re0.disco.domain.entity.RoleMenu;
import com.re0.disco.service.MenuService;
import com.re0.disco.service.RoleMenuService;
import com.re0.disco.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fangxi created by 2022/4/10
 */
public class RoleServiceTest extends ScalingDiscoApplicationTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;

    @Test
    public void save() {
        Role role = Role.builder()
            .name("admin")
            .level(1)
            .description("管理员")
            .build();
        roleService.save(role);
    }

    @Test
    public void bandRoleMenu() {
        RoleMenu roleMenu = RoleMenu.builder()
            .roleId(1512974750371065857L)
            .menuId(1514614440668999681L)
            .build();
        roleMenuService.save(roleMenu);
    }


    @Test
    public void bandAdminMenu() {
        Role role = roleService.selectByName("admin");
        Long adminId = role.getId();
        List<Menu> menuList = menuService.list();
        if (CollectionUtils.isEmpty(menuList)) {
            return;
        }
        roleMenuService.removeByRoleId(adminId);
        List<RoleMenu> roleMenuList = menuList.stream()
            .map(Menu::getId)
            .map(menuId -> RoleMenu.builder().roleId(adminId).menuId(menuId).build())
            .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenuList);
    }
}
