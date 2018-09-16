package com.world.cwwbike;

import com.world.cwwbike.security.AESUtil;
import com.world.cwwbike.security.Base64Util;
import com.world.cwwbike.security.RSAUtil;

public class DEMO {

    private static String PRIVATE_KEY ="";

    private static String PUBLIC_KEY ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkMzmWUaJ9Xm+qsE+PJ79J MPrjxTZirU1QaIVTjKXzw3YskkRQ6Wh7KgewBINR+H0QoGTVW8mhBF1ZDxI7 +aqqFgD3mOB4Ct1GTwt5a8Qf4n/auLhjXlt31h6qkI2HZFwuIO/c9xJ2d9Hs Ozjl+ZT+N13fd0/bwVxWVizRWjgJMQIDAQAB";
    public static void main(String[] args) throws Exception {

        //AES加密数据
        System.out.println("AES开始加密数据");
        String key = "1111111111111111";
        String datatoEn = "你好世界";
        String encrypt = AESUtil.encrypt(datatoEn, key);
        System.out.println("你好世界被加密的密文："+ encrypt);
        //RSA加密 AES的密玥 （key）
        System.out.println("RSA开始加密 AES的密玥 （key）");
        byte[] enkey = RSAUtil.encryptByPublicKey(key.getBytes("UTF-8"),"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuVRY8B3+Af5euC9WbgNkJKAiBzqOvrYi9mSST78jd4clpn7vkYHDfHzJiqFz9wjNRLzg9MUREF53bw9yhSljZ7F8JPMryfe8RR2Ed6CJq5nCy/2hvTTw4L6ypDemwe9f9yjIg52oPRPwU8lm8Uj3wKhjedDmZrkO1TAmt3sbQtwIDAQAB");
        System.out.println("加密的key的密文是"+ Base64Util.encode(enkey));
        //Base64Util防止乱码
        String base = Base64Util.encode(enkey);

    //服务端RSA解密AES的key ，在用解密的Key解密数据
        System.out.println("服务端RSA开始解密AES的key ");
       byte[] keybyte = RSAUtil.decryptByPrivateKey(Base64Util.decode(base));
        String keyR = new String(keybyte,"UTF-8");
        System.out.println("key是" + keyR);
        System.out.println("在用解密的Key解密数据 （key） ");
        String decrypt = AESUtil.decrypt(encrypt, key);
        System.out.println("你好世界被加密的明文:" + decrypt);
        System.out.println("key动态生成可以加强安全性:");
    }

}
