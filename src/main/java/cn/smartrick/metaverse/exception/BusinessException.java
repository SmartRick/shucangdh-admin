package cn.smartrick.metaverse.exception;


import cn.smartrick.metaverse.common.constant.ResponseCode;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected final Integer code;
    protected final String msg;

    public BusinessException(String msg) {
        super(msg);
        this.code = 500;
        this.msg = msg;
    }

    public BusinessException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public BusinessException(final Integer code, final String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public static BusinessException create(String message) {
        return new BusinessException(message);
    }

    public static BusinessException create(String message, int code) {
        return new BusinessException(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
