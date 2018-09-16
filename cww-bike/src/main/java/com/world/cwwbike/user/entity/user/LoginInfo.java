package com.world.cwwbike.user.entity.user;

import lombok.Data;


@Data
public class LoginInfo {
    /**登陆信息密文**/
    private String data;

    /**RSA加密的AES的密玥**/
    private String key;
}
