package ssm.util;

/**
 * create by 1311230692@qq.com on 2018/10/17 17:38
 * 使用秒嘀短信发送验证码
 * 配置文件
 **/
public class Config {
    /**
     * url 前半部分
     * */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822";

    /**
     * 开发者注册后系统自动生成的账号
     */
    public static final String ACCOUNT_SID = "5223a6ac653e44f392f44a155cc99ded";

    /**
     * 开发者注册后系统自动生成的TOKEN
     */
    public static final String AUTH_TOKEN = "79fd0fcddaff460a8d4b973ae7391f65";

    /**
     * 响应数据类型, JSON或XML
     */
    public static final String RESP_DATA_TYPE = "json";
}
