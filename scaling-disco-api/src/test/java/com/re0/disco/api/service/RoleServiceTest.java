package com.re0.disco.api.service;

import com.re0.disco.api.ScalingDiscoApplicationTest;
import com.re0.disco.domain.entity.Role;
import com.re0.disco.domain.entity.RoleMenu;
import com.re0.disco.service.RoleMenuService;
import com.re0.disco.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fangxi created by 2022/4/10
 */
public class RoleServiceTest extends ScalingDiscoApplicationTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;

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
            .menuId(1512983993962737665L)
            .build();
        roleMenuService.save(roleMenu);
    }

}
