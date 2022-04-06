package com.re0.disco.service;

import com.re0.disco.common.base.BaseService;
import com.re0.disco.domain.entity.UserRole;

import java.util.List;

/**
 * @author fangxi created by 2022/4/6
 */
public interface UserRoleService extends BaseService<UserRole> {

    List<Long> selectRoleIdsByUserId(Long userId);

}
