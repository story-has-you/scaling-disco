package com.re0.disco.common.base;

import com.re0.disco.common.result.PageRequest;
import com.re0.disco.common.result.PageResponse;
import com.re0.disco.domain.entity.BaseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<Entity extends BaseEntity, Service extends BaseService<Entity>> {

    @Autowired
    protected Service baseService;

    @ApiOperation("新增")
    @PostMapping("/save")
    public Long save(@RequestBody Entity entity) {
        boolean created = baseService.save(entity);
        return created ? entity.getId() : null;
    }

    @ApiOperation("批量新增")
    @PostMapping("/batch/save")
    public Boolean saveBatch(@RequestBody List<Entity> entities) {
        return baseService.saveBatch(entities);
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    public Boolean remove(@PathVariable Long id) {
        return baseService.removeById(id);
    }

    @ApiOperation("根据id批量新增")
    @DeleteMapping("/batch/remove")
    public Boolean removeBatch(@RequestBody List<Long> ids) {
        return baseService.removeByIds(ids);
    }

    @ApiOperation("更新")
    @PutMapping("/update")
    public Boolean update(@RequestBody Entity entity) {
        return baseService.updateById(entity);
    }

    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public Entity get(@PathVariable Long id) {
        return baseService.get(id);
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public PageResponse<Entity> page(@ModelAttribute @Validated PageRequest pageRequest,
                                     @ModelAttribute Entity entity) {
        return baseService.page(pageRequest.getCurrent(), pageRequest.getLimit(), entity);
    }
}
