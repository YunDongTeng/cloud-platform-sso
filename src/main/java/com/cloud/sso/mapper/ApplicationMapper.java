package com.cloud.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.sso.entity.Application;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationMapper extends BaseMapper<Application> {

    Application getByClientId(String clientId);

}
