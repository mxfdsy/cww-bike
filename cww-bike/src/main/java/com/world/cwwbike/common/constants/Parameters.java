package com.world.cwwbike.common.constants;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统参数
 */
@Component
@Data
public class Parameters {

//    @Value("#{'${security.noneSecurityPath}'.split(',')}")
//    private  List<String> noneSecurityPath;

    /*****redis config start*******/
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;
//    @Value("${redis.auth}")
//    private String redisAuth;
    @Value("${spring.redis.pool.max-idle}")
    private int redisMaxTotal;
    @Value("${spring.redis.pool.max-active}")
    private int redisMaxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private int redisMaxWaitMillis;
    /*****redis config end*******/

}
