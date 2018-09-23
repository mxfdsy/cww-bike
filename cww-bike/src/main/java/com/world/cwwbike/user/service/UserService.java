package com.world.cwwbike.user.service;

import com.world.cwwbike.common.exception.MaMaBikeException;
import com.world.cwwbike.user.entity.user.User;

public interface UserService {

    String login(String data, String key) throws MaMaBikeException;

    void modifyNickName(User user)throws MaMaBikeException;

    void sendVercode(String mobile, String ipFromRequest) throws MaMaBikeException;
}
