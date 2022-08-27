package cn.smartrick.metaverse.domain.vo;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * 地址位置
 */
@Data
public class GpVo {
    //国家
    private String country;
    //区域
    private String region;
    //省份
    private String provinces;
    //城市
    private String city;
    //IPS（运营商）
    private String Ips;

    public static GpVo parserGpStr(String ipAddress) {
        if (StrUtil.isBlank(ipAddress)) {
            throw new IllegalArgumentException("参数不能为空");
        }
        String[] split = ipAddress.split("\\|");
        if (split.length != 5) {
            throw new IllegalArgumentException("参数有误：解构长度为" + split.length);
        }
        GpVo gpVo = new GpVo();
        gpVo.setCountry(split[0]);
        gpVo.setRegion(split[1]);
        gpVo.setProvinces(split[2]);
        gpVo.setCity(split[3]);
        gpVo.setIps(split[4]);
        return gpVo;
    }
}
