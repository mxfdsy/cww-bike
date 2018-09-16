package com.world.cwwbike.user.service;

import com.world.cwwbike.common.exception.MaMaBikeException;

public interface UserService {

    String login(String data, String key) throws MaMaBikeException;
}
