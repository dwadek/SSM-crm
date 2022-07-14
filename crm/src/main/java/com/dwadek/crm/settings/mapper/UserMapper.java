package com.dwadek.crm.settings.mapper;

import com.dwadek.crm.settings.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 根据账号和密码查询用户
     * @param map
     * @return
     */
    User selectUserByLoginActAndPwd(Map<String,Object> map);

    List<User> selectAllUsers();
}
