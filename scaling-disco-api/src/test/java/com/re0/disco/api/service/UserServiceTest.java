package com.re0.disco.api.service;

import com.re0.disco.api.ScalingDiscoApplicationTest;
import com.re0.disco.domain.entity.Role;
import com.re0.disco.domain.entity.User;
import com.re0.disco.domain.entity.UserRole;
import com.re0.disco.service.RoleService;
import com.re0.disco.service.UserRoleService;
import com.re0.disco.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author fangxi created by 2022/4/10
 */
public class UserServiceTest extends ScalingDiscoApplicationTest {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Test
    public void bandUserRole() {
        Long roleId = Optional.ofNullable(roleService.selectByName("admin")).map(Role::getId).orElse(null);
        Long userId = Optional.ofNullable(userService.selectByUsername("admin")).map(User::getId).orElse(null);
        if (ObjectUtils.allNotNull(roleId, userId)) {
            UserRole userRole = UserRole.builder()
                .roleId(roleId)
                .userId(userId)
                .build();
            userRoleService.save(userRole);
        }
    }
}
