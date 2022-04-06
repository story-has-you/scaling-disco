package com.re0.disco.common.base;

import com.re0.disco.common.result.PageRequest;
import com.re0.disco.common.result.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<Entity extends BaseEntity, Service extends BaseService<Entity>> {

    @Autowired
    protected Service baseService;

    @PostMapping("/save")
    public Long save(@RequestBody Entity entity) {
        boolean created = baseService.save(entity);
        return created ? entity.getId() : null;
    }

    @PostMapping("/batch/save")
    public Boolean saveBatch(@RequestBody List<Entity> entities) {
        return baseService.saveBatch(entities);
    }

    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable Long id) {
        return baseService.removeById(id);
    }

    @DeleteMapping("/batch/remove")
    public Boolean removeBatch(@RequestBody List<Long> ids) {
        return baseService.removeByIds(ids);
    }

    @PutMapping("/update")
    public Boolean update(@RequestBody Entity entity) {
        return baseService.updateById(entity);
    }

    @GetMapping("/{id}")
    public Entity get(@PathVariable Long id) {
        return baseService.get(id);
    }

    @GetMapping("/page")
    public PageResponse<Entity> page(@ModelAttribute @Validated PageRequest pageRequest,
                                     @ModelAttribute Entity entity) {
        return baseService.page(pageRequest.getCurrent(), pageRequest.getLimit(), entity);
    }
}
