package com.world.cwwbike.controller;

import com.alibaba.fastjson.JSON;
import com.world.cwwbike.dao.UserMapper;
import com.world.cwwbike.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    UserMapper userMapper;

    @Value("${spring.config.name}")
    String cww;

    @RequestMapping("/test")
    public String test() {
        User user = userMapper.selectByPrimaryKey(1L);
        System.out.println(cww);
        return JSON.toJSONString(user);
    }
}
