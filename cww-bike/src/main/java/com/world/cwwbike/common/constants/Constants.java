package com.world.cwwbike.common.constants;

public class Constants {
    /**用户token**/
    public static final String REQUEST_TOKEN_KEY = "user-token";
    /**客户端版本**/
    public static final String REQUEST_VERSION_KEY = "version";
    /**客户端平台 android/ios**/
    public static final String REQUEST_PLATFORM_KEY = "platform";

    public static final String REQUEST_TYPE_KEY = "type";
    /**自定义状态码 start**/
    public static final int RESP_STATUS_OK = 200;

    public static final int RESP_STATUS_NOAUTH = 401;

    public static final int RESP_STATUS_INTERNAL_ERROR = 500;

    public static final int RESP_STATUS_BADREQUEST = 400;
    /**自定义状态码 end**/

    /**秒滴SMS start**/
    public static final String MDSMS_ACCOUNT_SID = "46bc7f418588405189ef6414d15c4551";

    public static final String MDSMS_AUTH_TOKEN = "692cd34097864b31807953b3c6b67ca5";

    public static final String MDSMS_REST_URL = "https://api.miaodiyun.com/20150822";

    public static final String MDSMS_VERCODE_TPLID = "660829978";

    /**秒滴SMS end**/


    /***七牛keys start****/
    public static final String QINIU_ACCESS_KEY="";

    public static final String QINIU_SECRET_KEY="";

    public static final String QINIU_HEAD_IMG_BUCKET_NAME="";

    public static final String QINIU_HEAD_IMG_BUCKET_URL="";

    /***七牛keys end****/

    /**百度云推送 start**/
    public static final String BAIDU_YUN_PUSH_API_KEY="";

    public static final String BAIDU_YUN_PUSH_SECRET_KEY="";

    public static final String CHANNEL_REST_URL = "api.push.baidu.com";
    /**百度云推送end**/
}
