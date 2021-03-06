package com.world.cwwbike.common;


import com.world.cwwbike.cache.CommonCacheUtil;
import com.world.cwwbike.common.constants.Constants;
import com.world.cwwbike.user.entity.user.UserElement;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by JackWangon[www.coder520.com] 2017/8/4.
 */
@Log4j
public class BaseController  {

    @Autowired
    private CommonCacheUtil cacheUtil;

    protected UserElement getCurrentUser() {
        //获取当前用户请求的信息
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader(Constants.REQUEST_TOKEN_KEY);
        if (StringUtils.isNotEmpty(token)) {
            try {
                UserElement ue = cacheUtil.getUserByToken(token);
                return ue;
            } catch (Exception e) {
                log.error("fail to get ue", e);
                return null;
            }
        }
        return null;
    }

    protected String getIpFromRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        //若直接获取    ip = request.getRemoteAddr(); 可能会拿到转发节点的iP 所以
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
}
