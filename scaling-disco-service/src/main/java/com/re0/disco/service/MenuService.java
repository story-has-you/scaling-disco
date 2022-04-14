package com.re0.disco.service;

import com.re0.disco.common.base.BaseService;
import com.re0.disco.common.utils.SecurityUtils;
import com.re0.disco.domain.entity.Menu;
import com.re0.disco.domain.model.AuthUser;
import com.re0.disco.domain.vo.response.MenuVO;

import java.util.List;

/**
 * @author fangxi created by 2022/4/6
 */
public interface MenuService extends BaseService<Menu> {

    List<MenuVO> buildMenuTree();

    default List<Menu> selectByCurrentUser() {
        AuthUser authUser = SecurityUtils.getCurrentUser();
        return selectByCurrentUser(authUser);
    }

    List<Menu> selectByCurrentUser(AuthUser authUser);


}
