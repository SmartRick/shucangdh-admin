package cn.smartrick.metaverse.common.constant;

/**
 * @Date: 2021/12/19
 * @Author: SmartRick
 * @Description: 系统通用常量
 */
public class CommonConst {
    //错误属性key
    public static final String ERROR_KEY = "ERROR";
    public static final String ERROR_HANDLER_PATH = "/errorHandler";

    //================= redis prefix =================

    public static final String PREFIX_COMMON = "NFT:";
    //ip验证标识
    public static final String PREFIX_CAPTCHA = PREFIX_COMMON + "CAPTCHA:IP:";
    //ip登录标识
    public static final String PREFIX_LOGIN_RECORD_IP = PREFIX_COMMON + "LOGIN:IP:";
    //ip登录禁止标识
    public static final String PREFIX_LOGIN_LOCKED_IP = PREFIX_COMMON + "LOGIN:LOCKED:IP:";
}
