package com.re0.disco.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fangxi created by 2022/4/24
 */
@Data
public class ColumnInfo {

    @ApiModelProperty("字段名称")
    private String name;
    @ApiModelProperty("字段注释")
    private String comment;
    @ApiModelProperty("字段类型")
    private String type;

}
