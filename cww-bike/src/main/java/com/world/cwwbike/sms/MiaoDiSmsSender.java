package com.world.cwwbike.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.world.cwwbike.common.constants.Constants;
import com.world.cwwbike.common.utils.HttpUtil;
import com.world.cwwbike.security.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JackWangon[www.coder520.com] 2017/8/5.
 */
@Service("verCodeService")
@Slf4j
public class MiaoDiSmsSender implements SmsSender{


    private static String operation = "/industrySMS/sendSMS";


    /**
     *@Author JackWang [www.coder520.com]
     *@Date 2017/8/5 16:23
     *@Description  秒滴发送短信
     */
    @Override
    public  void sendSms(String phone,String tplId,String params){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = sdf.format(new Date());
            String sig = MD5Util.getMD5(Constants.MDSMS_ACCOUNT_SID +Constants.MDSMS_AUTH_TOKEN +timestamp);
            String url = Constants.MDSMS_REST_URL +operation;
//            Map<String,String> param = new HashMap<>();
//            param.put("accountSid",Constants.MDSMS_ACCOUNT_SID);
//            param.put("to",phone);
//            param.put("smsContent",tplId);
//            param.put("timestamp",timestamp);
//            param.put("sig",sig);
//            param.put("respDataType","json");

            String body = "accountSid=" + Constants.MDSMS_ACCOUNT_SID + "&to=" + phone + "&smsContent=" + getTxt(params)
                    + "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + "json";
            String result = HttpUtil.postMy(url,body);

            JSONObject jsonObject = JSON.parseObject(result);
            if(!jsonObject.getString("respCode").equals("00000")){
                log.error("fail to send sms to "+phone+":"+params+":"+result);
            }
        } catch (Exception e) {
            log.error("fail to send sms to "+phone+":"+params);
        }
    }

    private String getTxt(String params) {
        return  "【红岸基地】您的验证码为"+ params + "，请于1分钟内正确输入，如非本人操作，请忽略此短信。";
    }

}
