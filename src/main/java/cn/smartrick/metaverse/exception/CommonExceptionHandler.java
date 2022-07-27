package cn.smartrick.metaverse.exception;

import cn.smartrick.metaverse.common.constant.ResponseCode;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @author hu
 * @version 1.0
 * @description: 全局通用异常处理器
 * @date 2021/7/12
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {
//    /**
//     * 用于处理过滤器异常
//     *
//     * @param error
//     * @param request
//     * @param response
//     */
//    public static void exceptionHandle(Exception error, HttpServletRequest request, HttpServletResponse response) {
//        try {
//            request.setAttribute(CommonConst.ERROR_KEY, error);
//            request.getRequestDispatcher(CommonConst.ERROR_HANDLER_PATH).forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void exceptionHandle(Exception error) {
//        try {
//            HttpServletRequest request = ServletUtils.getRequest();
//            HttpServletResponse response = ServletUtils.getResponse();
//            request.setAttribute(CommonConst.ERROR_KEY, error);
//            request.getRequestDispatcher(CommonConst.ERROR_HANDLER_PATH).forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @ExceptionHandler(Exception.class)
    public ResponseDTO runtimeExceptionHandler(Exception e, HttpServletRequest req, HttpServletResponse resp) {
        log.error("请求发生错误：" + e.getMessage(), e);
        ResponseDTO res = ResponseDTO.fail();
        if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException exception = (HttpRequestMethodNotSupportedException) e;
            res = ResponseDTO.wrap(ResponseCode.REQ_METHOD_ERROR.embedded(Arrays.toString(exception.getSupportedMethods())));
            resp.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (e instanceof HttpMessageNotReadableException) {
//            处理请求体不可读、未携带请求体，但参数必须读的异常情况
            res = ResponseDTO.wrap(ResponseCode.PARAM_NOT_COMPLETE);
            resp.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (e instanceof MethodArgumentNotValidException) {
//            请求参数校验失败
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = exception.getBindingResult();
            Map<String, String> errMap = new HashMap<>();
            for (ObjectError err : bindingResult.getAllErrors()) {
                errMap.put(((DefaultMessageSourceResolvable) err.getArguments()[0]).getDefaultMessage(), err.getDefaultMessage());
            }
            res = ResponseDTO.wrap(ResponseCode.PARAM_ERROR, errMap);
        } else if (e instanceof BusinessException) {
            BusinessException exception = (BusinessException) e;
            res = ResponseDTO.failMsg(exception.getMsg());
        } else {
            res = ResponseDTO.failMsg("服务器发生错误，请联系管理人员进行反馈");
        }

        return res;
    }
}
