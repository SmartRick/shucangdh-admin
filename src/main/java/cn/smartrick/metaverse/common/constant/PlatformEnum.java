package cn.smartrick.metaverse.common.constant;

/**
 * 平台类型枚举
 */
public enum PlatformEnum {
    WX_GZH("微信公众号"),
    WX_XCX("微信小程序"),
    BD_XCX("百度小程序"),
    YSF_XCX("云闪付小程序"),
    ZFB_XCX("支付宝小程序"),
    APP("APP客户端"),
    H5("网页端");

    private String fullName;

    PlatformEnum(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
