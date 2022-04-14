package com.re0.disco.domain.vo.response;

import com.re0.disco.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fangxi created by 2022/4/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {

    private Long id;
    private Long pid;
    private String path;
    private String name;
    private String icon;
    private String access;
    private String component;
    private List<MenuVO> routes;


    public MenuVO(Menu menu) {
        this.id = menu.getId();
        this.pid = menu.getPid();
        this.path = menu.getPath();
        this.name = menu.getTitle();
        this.icon = menu.getIcon();
        this.access = menu.getPermission();
        this.component = menu.getComponentName();
    }


}
