package com.example.demo.mapper;

import com.example.demo.datasource.TargetDataSource;
import com.example.demo.entity.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    //@TargetDataSource("db1")
    User getUserInfo(User user);

}