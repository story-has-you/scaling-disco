package com.re0.disco.api.service;

import com.re0.disco.api.ScalingDiscoApplicationTest;
import com.re0.disco.domain.entity.Menu;
import com.re0.disco.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fangxi created by 2022/4/10
 */
public class MenuServiceTest extends ScalingDiscoApplicationTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void save() {
        Menu menu = Menu.builder()
            .pid(1512983993962737665L)
            .subCount(0)
            .level(2)
            .title("仪表盘1")
            .componentName("Dashboard1")
            .path("/dashboard1")
            .icon("dashboard1")
            .sort(1)
            .build();
        menuService.save(menu);
    }

}
