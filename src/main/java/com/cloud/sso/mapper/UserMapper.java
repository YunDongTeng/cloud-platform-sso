package com.cloud.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.sso.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getByUserName(String userName);

}
