package com.world.cwwbike.user.controller;

import com.world.cwwbike.common.BaseController;
import com.world.cwwbike.common.constants.Constants;
import com.world.cwwbike.common.exception.MaMaBikeException;
import com.world.cwwbike.common.resp.ApiResult;
import com.world.cwwbike.user.dao.UserMapper;
import com.world.cwwbike.user.entity.user.LoginInfo;
import com.world.cwwbike.user.entity.user.User;
import com.world.cwwbike.user.entity.user.UserElement;
import com.world.cwwbike.user.service.UserService;
import lombok.extern.log4j.Log4j;
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
public class UserController extends BaseController {

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


    /**
     *@Author JackWang [www.coder520.com]
     *@Date 2017/8/4 11:38
     *@Description 修改用户昵称
     */
    @RequestMapping("/modifyNickName")
    public ApiResult modifyNickName(@RequestBody User user){

        ApiResult<String> resp = new ApiResult<>();
        try {
            //若直接从前端获取id 是不安全的 应该从数据库拿
            UserElement ue = getCurrentUser();
            user.setId(ue.getUserId());
            userService.modifyNickName(user);
            resp.setMessage("更新成功");
        } catch (MaMaBikeException e) {
            resp.setCode(e.getStatusCode());
            resp.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("Fail to update user info", e);
            resp.setCode(Constants.RESP_STATUS_INTERNAL_ERROR);
            resp.setMessage("内部错误");
        }

        return resp;
    }




}
