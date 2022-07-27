package cn.smartrick.metaverse.common.domain;


import cn.smartrick.metaverse.common.constant.ResponseCode;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Date: 2021年11月24日11:01:50
 * @Author: SmartRick
 * @Description: 通用返回
 */
public class ResponseDTO<T> {
    @ApiModelProperty(value = "状态码")
    protected Integer code;

    @ApiModelProperty(value = "返回消息")
    protected String msg;

    @ApiModelProperty(value = "是否成功")
    protected Boolean success;

    @ApiModelProperty(value = "返回数据")
    protected T data;

    private ResponseDTO() {
    }

    public ResponseDTO(ResponseCode ResponseCode, String msg) {
        this.code = ResponseCode.getCode();
        this.msg = msg;
        this.success = ResponseCode.isSuccess();
    }

    public ResponseDTO(ResponseCode ResponseCode, T data) {
        super();
        this.code = ResponseCode.getCode();
        this.msg = ResponseCode.getMsg();
        this.data = data;
        this.success = ResponseCode.isSuccess();
    }

    public ResponseDTO(ResponseCode ResponseCode, T data, String msg) {
        super();
        this.code = ResponseCode.getCode();
        this.msg = msg;
        this.data = data;
        this.success = ResponseCode.isSuccess();
    }

    private ResponseDTO(ResponseCode ResponseCode) {
        this.code = ResponseCode.getCode();
        this.msg = ResponseCode.getMsg();
        this.success = ResponseCode.isSuccess();
    }

    public ResponseDTO(ResponseDTO<T> responseDTO) {
        this.code = responseDTO.getCode();
        this.msg = responseDTO.getMsg();
        this.success = responseDTO.isSuccess();
    }

    //默认不返回msg
    public static <T> ResponseDTO<T> succ() {
        return new ResponseDTO<T>(ResponseCode.SUCCESS_DATA);
    }

    //需要返回msg
    public static <T> ResponseDTO<T> succMsg() {
        return new ResponseDTO<T>(ResponseCode.SUCCESS);
    }

    public static <T> ResponseDTO<T> exprMapper(int res, String prefix) {
        return expr(res >= 1, prefix);
    }

    public static <T> ResponseDTO<T> expr(boolean e, String prefix) {
        return e ? succMsg(prefix + "成功") : failMsg(prefix + "失败");
    }


    public static <T> ResponseDTO<T> exprMapper(int res) {
        return expr(res >= 1);
    }

    public static <T> ResponseDTO<T> expr(boolean e) {
        return e ? succMsg() : fail();
    }

    public static <T> ResponseDTO<T> succData(T data, String msg) {
        return new ResponseDTO<T>(ResponseCode.SUCCESS_DATA, data, msg);
    }

    public static <T> ResponseDTO<T> succData(T data) {
        return new ResponseDTO<T>(ResponseCode.SUCCESS_DATA, data);
    }

    public static <T> ResponseDTO<T> succMsg(String msg) {
        return new ResponseDTO<T>(ResponseCode.SUCCESS, msg);
    }

    public static <T> ResponseDTO<T> fail() {
        return new ResponseDTO<T>(ResponseCode.FAIL);
    }

    public static <T> ResponseDTO<T> failData(T data) {
        return new ResponseDTO<T>(ResponseCode.FAIL, data);
    }

    public static <T> ResponseDTO<T> failData(T data, String msg) {
        return new ResponseDTO<T>(ResponseCode.FAIL, data, msg);
    }

    public static <T> ResponseDTO<T> failMsg(String msg) {
        return new ResponseDTO<T>(ResponseCode.FAIL, msg);
    }

    public static <T> ResponseDTO<T> wrap(ResponseCode codeConst) {
        return new ResponseDTO<>(codeConst);
    }

    public static <T> ResponseDTO<T> wrap(ResponseCode codeConst, T t) {
        return new ResponseDTO<T>(codeConst, t);
    }

    public static <T> ResponseDTO<T> wrapMsg(ResponseCode codeConst, String msg) {
        return new ResponseDTO<T>(codeConst, msg);
    }

    public String getMsg() {
        return msg;
    }

    public ResponseDTO<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResponseDTO<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseDTO<T> setData(T data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" + "code=" + code + ", msg='" + msg + '\'' + ", success=" + success + ", data=" + data +
                '}';
    }
}
