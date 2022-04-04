package com.re0.common.result;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The type Page response.
 *
 * @param <T> the type parameter
 * @author fangxi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> implements Serializable {
    /**
     * 数据
     */
    private List<T> rows = Lists.newArrayList();
    /**
     * 当前页
     */
    private Long current;
    /**
     * 每页的数量
     */
    private Long size;
    /**
     * 总记录数
     */
    private Long records;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 是否有更多
     */
    private Boolean hasNext;

}

