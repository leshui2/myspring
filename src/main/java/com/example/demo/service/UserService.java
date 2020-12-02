package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 *
 */
@Service
@Slf4j
public class UserService {
    Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserMapper userMapper;

    public User getUserInfo(User user) {
        User userInfo = userMapper.getUserInfo(user);
        return userInfo;
    }

    //注册
    public int insert(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }

}
