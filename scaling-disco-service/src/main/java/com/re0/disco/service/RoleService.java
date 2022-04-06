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

}
