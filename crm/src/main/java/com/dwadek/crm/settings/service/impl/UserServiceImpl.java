package com.dwadek.crm.settings.service.impl;

import com.dwadek.crm.settings.mapper.UserMapper;
import com.dwadek.crm.settings.pojo.User;
import com.dwadek.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }
}

