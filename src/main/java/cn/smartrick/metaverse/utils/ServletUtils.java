package cn.smartrick.metaverse.utils;

import cn.smartrick.metaverse.common.constant.ServletConstant;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Servlet工具
 */
@Slf4j
public class ServletUtils {
    public static final String DEFAULT_PARAMS_PARAM = "params";
    public static final String DEFAULT_PARAM_PREFIX_PARAM = "param_";

    /**
     * 获取当前请求对象
     * controller.xml: <listener><listener-class>
     * org.springframework.controller.context.request.RequestContextListener
     * </listener-class></listener>
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            return request;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 获取当前请求对象
     * controller.xml: <listener><listener-class>
     * org.springframework.controller.context.request.RequestContextListener
     * </listener-class></listener>
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = null;
        try {
            response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
            return response;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    /**
     * Describe: 获取 HttpServletSession 对象
     * Param null
     * Return HttpServletSession
     */
    public static HttpSession getSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest().getSession();
    }

    /**
     * Describe: 判断是否为 Ajax 请求
     * Param null
     * Return HttpServletSession
     */
    public static Boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader(ServletConstant.Header.X_REQUESTED_WITH);
        if (ServletConstant.Header.XML_HTTP_REQUEST.equals(requestType)) {
            return true;
        } else {
            return false;
        }
    }

    public static void writeJson(HttpServletResponse resp, Object data) {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
            writer.write(JSON.toJSON(data).toString());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static void writeCurrJson(Object data) {
        HttpServletResponse resp = getResponse();
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
            writer.write(JSON.toJSON(data).toString());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 获取查询参数
     */
    public static String getQueryParam() {
        return getRequest().getQueryString();
    }

    /**
     * 获取请求地址
     */
    public static String getRequestURI() {
        return getRequest().getRequestURI();
    }

    /**
     * 获取客户端地址
     */
    public static String getRemoteHost() {
        String remoteHost = getRequest().getRemoteHost();
        if (ServletConstant.REMOTE_HOST_0.equals(remoteHost)) {
            remoteHost = ServletConstant.REMOTE_HOST_127;
        }
        return remoteHost;
    }

    /**
     * 获取当前请求方法
     */
    public static String getMethod() {
        return getRequest().getMethod();
    }

    /**
     * 获取请求头
     */
    public static String getHeader(String name) {
        return getRequest().getHeader(name);
    }

    /**
     * 获取 UserAgent
     */
    public static String getAgent() {
        return getHeader(ServletConstant.Header.UA);
    }

    /**
     * 获取浏览器类型
     */
    public static String getBrowser() {
        String userAgent = getAgent();
        if (userAgent.contains(ServletConstant.Browser.FIRE_FOX_UA)) {
            return ServletConstant.Browser.FIRE_FOX_NAME;
        } else if (userAgent.contains(ServletConstant.Browser.CHROME_UA)) {
            return ServletConstant.Browser.CHROME_NAME;
        } else if (userAgent.contains(ServletConstant.Browser.IE_UA)) {
            return ServletConstant.Browser.IE_NAME;
        } else {
            return ServletConstant.Browser.UNKNOWN;
        }
    }

    /**
     * 获取浏览器类型
     */
    public static String getSystem() {
        String userAgent = getAgent();
        if (userAgent.toLowerCase().contains(ServletConstant.System.WIN_UA)) {
            return ServletConstant.System.WIN_NAME;
        } else if (userAgent.toLowerCase().contains(ServletConstant.System.MAC_UA)) {
            return ServletConstant.System.MAC_NAME;
        } else if (userAgent.toLowerCase().contains(ServletConstant.System.UNIX_UA)) {
            return ServletConstant.System.UNIX_NAME;
        } else if (userAgent.toLowerCase().contains(ServletConstant.System.ANDROID_UA)) {
            return ServletConstant.System.ANDROID_NAME;
        } else if (userAgent.toLowerCase().contains(ServletConstant.System.IPHONE_UA)) {
            return ServletConstant.System.IPHONE_NAME;
        } else {
            return ServletConstant.System.UNKNOWN + userAgent;
        }
    }
}
