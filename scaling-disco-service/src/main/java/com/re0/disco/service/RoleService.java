package com.re0.disco.service;

import com.re0.disco.common.base.BaseService;
import com.re0.disco.domain.entity.Role;

import java.util.List;

/**
 * @author fangxi created by 2022/4/6
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 根据用户id查询角色
     */
    List<Role> selectByUserId(Long userId);

    /**
     * 根据角色名称查询
     */
    Role selectByName(String name);

    /**
     * 查询当前登录用户的角色列表
     */
    List<Role> selectByCurrentUser();
}
