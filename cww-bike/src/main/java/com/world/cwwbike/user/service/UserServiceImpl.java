package com.world.cwwbike.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.world.cwwbike.cache.CommonCacheUtil;
import com.world.cwwbike.common.exception.MaMaBikeException;
import com.world.cwwbike.security.AESUtil;
import com.world.cwwbike.security.Base64Util;
import com.world.cwwbike.security.MD5Util;
import com.world.cwwbike.security.RSAUtil;
import com.world.cwwbike.user.dao.UserMapper;
import com.world.cwwbike.user.entity.user.User;
import com.world.cwwbike.user.entity.user.UserElement;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
@Log4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private CommonCacheUtil cacheUtil;

    @Override
    public String login(String data, String key) throws MaMaBikeException {
        String decryptData = null;
        String token = null;
        try {
            //RSA解密AES的key
            byte[] aesKey = RSAUtil.decryptByPrivateKey(Base64Util.decode(key));
            //AES的key解密AES加密数据
            decryptData = AESUtil.decrypt(data, new String(aesKey, "UTF-8"));
            if (decryptData == null) {
                throw new Exception();
            }
            //拿到提交数据 开始验证逻辑
            JSONObject jsonObject = JSON.parseObject(decryptData);
            String mobile = jsonObject.getString("mobile");//电话
            String code = jsonObject.getString("code");//验证码
            String platform = jsonObject.getString("platform");//机器类型
            String channelId = jsonObject.getString("channelId");//推送频道编码 单个设备唯一

            if (StringUtils.isBlank(mobile) || StringUtils.isBlank(code)||StringUtils.isBlank(platform)) {
                throw new Exception();
            }
            //去redis取验证码比较手机号码和验证码是否匹配 若匹配 说明是本人手机
            String verCode = cacheUtil.getCacheValue(mobile);
            User user = null;
            if (code.equals(verCode)) {
                //检查用户是否存在
                user = userMapper.selectByMobile(mobile);
                if (user == null) {
                    //用户不存在 帮用户注册
                    user = new User();
                    user.setMobile(mobile);
                    user.setNickname(mobile);
                    userMapper.insertSelective(user);
                }
            } else {
                throw new MaMaBikeException("手机号与验证码不匹配");
            }
            //生成token
            try {
                token = this.generateToken(user);
            } catch (Exception e) {
                throw new MaMaBikeException("fail.to.generate.token");
            }

            UserElement ue = new UserElement();
            ue.setMobile(mobile);
            ue.setUserId(user.getId());
            ue.setToken(token);
            ue.setPlatform(platform);
            ue.setPushChannelId(channelId);
            cacheUtil.putTokenWhenLogin(ue);


        } catch (Exception e) {
            log.error("Fail to decrypt data", e);
            throw new MaMaBikeException("数据解析错误");
        }

        return token;
    }

    private String generateToken(User user)
            throws Exception {
        String source = user.getId() + ":" + user.getMobile() +  ":"+ System.currentTimeMillis();
        return MD5Util.getMD5(source);
    }


}
