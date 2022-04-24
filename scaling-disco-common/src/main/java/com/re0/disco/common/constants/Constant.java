package com.re0.disco.common.constants;

/**
 * @author fangxi created by 2022/4/24
 */
public interface Constant {

    String TABLE_INFO_SQL = "select column_name as'name',column_comment as'comment',column_type as'type' from information_schema.columns where table_schema='%s' and table_name='%s';";

}
