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
            .pid(null)
            .subCount(0)
            .type(1)
            .level(1)
            .title("系统监控")
            .componentName("Monitor")
            .path("/monitor")
            .icon("monitor")
            .sort(1)
            .build();
        menuService.save(menu);
    }

}
