package com.nanjing.testsecurity.mapper;

import com.nanjing.testsecurity.pojo.UserInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserInfoBeanMapper {
    //查询用户信息
    @Select("select * from user where username = #{username}")
    UserInfoBean selectByUsername(@Param("username") String username);
}
