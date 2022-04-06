package com.re0.disco.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.re0.disco.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fangxi created by 2022/4/6
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}