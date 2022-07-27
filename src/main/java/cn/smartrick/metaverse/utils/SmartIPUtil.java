package cn.smartrick.metaverse.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * [ ip工具箱 ]
 *
 * @author yandanyang
 * @version 1.0
 * @company 1024lab.net
 * @copyright (c) 2018 1024lab.netInc. All rights reserved.
 * @date 2019/5/5 0005 下午 15:34
 * @since JDK1.8
 */
public class SmartIPUtil {

    private static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    /**
     * 获取本机ip
     *
     * @return String 本机ip
     */
    public static String getLocalHostIP() {
        // 本地IP，如果没有配置外网IP则返回它
        String localIp = null;
        // 外网IP
        String netIp = null;
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            // 是否找到外网IP
            boolean finded = false;
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    // 外网IP
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                        netIp = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                        // 内网IP
                        localIp = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.getMessage();
        }
        if (netIp != null && !"".equals(netIp)) {
            return netIp;
        } else {
            return localIp;
        }
    }

    /**
     * 获取请求ip
     *
     * @param request http请求对象
     * @return String 请求端ip
     */
    public static String getRemoteIp(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = getXForwardedForIp(request);
        if (ipValid(ip)) {
            return realIp(ip);
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (ipValid(ip)) {
            return realIp(ip);
        }
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (ipValid(ip)) {
            return realIp(ip);
        }
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ipValid(ip)) {
            return realIp(ip);
        }

        ip = request.getRemoteAddr();
        return realIp(ip);
    }

    private static String getXForwardedForIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        //ip 无效直接返回
        if (!ipValid(ip)) {
            return "";
        }
        if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String strIp : ips) {
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
            return ip;
        }
        return ip;
    }

    private static Boolean ipValid(String ip) {
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            return false;
        }
        return true;
    }

    private static String realIp(String ip) {
        if (StringUtils.isEmpty(ip)) {
            return "";
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static String getRemoteLocation(HttpServletRequest request) {
        String ip = getRemoteIp(request);
        return getIpLocation(ip);
    }

    /**
     * 获取 IP 地理位置
     *
     * @param ip ip
     * @return String 地理位置
     */
    public static String getIpLocation(String ip) {
        String location = "未知";
        if (StringUtils.isEmpty(ip)) {
            return location;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("ip", ip);

        try {

            String rspStr = HttpUtil.get(IP_URL, param);
            if (StringUtils.isEmpty(rspStr)) {
                return location;
            }
            JSONObject jsonObject = JSON.parseObject(rspStr);
            String data = jsonObject.getString("data");
            JSONObject jsonData = JSON.parseObject(data);
            String region = jsonData.getString("region");
            String city = jsonData.getString("city");
            location = region + " " + city;
            if (location.contains("内网IP")) {
                location = "内网(" + ip + ")";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public static void main(String[] args) {
        System.out.printf(getIpLocation("172.16.0.221"));
    }

}
