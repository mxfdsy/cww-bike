package com.world.cwwbike.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.SerializableString;
import com.world.cwwbike.common.constants.Constants;
import com.world.cwwbike.common.exception.MaMaBikeException;
import com.world.cwwbike.common.resp.ApiResult;
import com.world.cwwbike.dao.UserMapper;
import com.world.cwwbike.entity.user.LoginInfo;
import com.world.cwwbike.entity.user.User;
import com.world.cwwbike.service.UserService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Log4j
public class UserController {

    @Autowired
    UserMapper userMapper;


    @Autowired
    UserService userService;

    @Value("${spring.config.name}")
    String cww;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResult<String>  login(@RequestBody LoginInfo loginInfo) {
        ApiResult<String> resp = new ApiResult<>();
        try {
            String data = loginInfo.getData();
            String key = loginInfo.getKey();
            if (data == null) {
                throw new MaMaBikeException("非法请求");
            }
            String token = userService.login(data, key);
            resp.setData(token);
        } catch (MaMaBikeException e) {
            resp.setCode(e.getStatusCode());
            resp.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("Fail to login", e);
            resp.setCode(Constants.RESP_STATUS_INTERNAL_ERROR);
            resp.setMessage("内部错误");
        }
        return resp;
    }
}
