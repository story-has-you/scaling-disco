package com.re0.disco.common.result;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.re0.disco.common.utils.BeanUtils;
import com.re0.disco.common.utils.CollectionUtils;

import java.util.List;
import java.util.function.Function;

/**
 * The type Page response.
 *
 * @author fangxi
 */
public final class PageResponseBuilder {

    public static <R, T> PageResponse<R> of(Function<T, R> function, Page<T> page) {
        List<T> records = page.getRecords();
        PageResponse<R> response = getResponse(page.getCurrent(), page.getSize(), page.getTotal(), page.getPages(), page.hasNext());
        response.setRows(CollectionUtils.map(records, function));
        return response;
    }

    public static <R, T> PageResponse<R> of(Page<T> page, Class<R> clazz) {
        List<T> records = page.getRecords();
        List<R> rows = BeanUtils.copyProperties(records, clazz);
        PageResponse<R> response = getResponse(page.getCurrent(), page.getSize(), page.getTotal(), page.getPages(), page.hasNext());
        response.setRows(rows);
        return response;
    }

    public static <T> PageResponse<T> of(Page<T> page) {
        return of(Function.identity(), page);
    }

    public static <T, R> PageResponse<R> of(List<R> rows, Page<T> page) {
        PageResponse<R> response = getResponse(page.getCurrent(), page.getSize(), page.getTotal(), page.getPages(), page.hasNext());
        response.setRows(rows);
        return response;
    }

    public static <T, R> PageResponse<T> empty(Page<R> page) {
        return getResponse(page.getCurrent(), page.getSize(), 0, 0, false);
    }

    public static <T> PageResponse<T> empty(long current, long limit) {
        return getResponse(current, limit, 0, 0, false);
    }

    public static <T> PageResponse<T> empty() {
        return getResponse(0, 0, 0, 0, false);
    }

    private static <R> PageResponse<R> getResponse(long current, long size, long total, long pages, boolean b) {
        PageResponse<R> response = new PageResponse<>();
        response.setCurrent(current);
        response.setSize(size);
        response.setRecords(total);
        response.setPages(pages);
        response.setHasNext(b);
        return response;
    }

}

