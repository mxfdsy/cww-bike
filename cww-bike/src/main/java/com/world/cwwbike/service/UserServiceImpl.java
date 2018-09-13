package com.world.cwwbike.service;

import com.world.cwwbike.dao.UserMapper;
import com.world.cwwbike.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String login() {
        User user = new User();
        user.setId(1l);
        userMapper.insert(user);
        return null;
    }
}
