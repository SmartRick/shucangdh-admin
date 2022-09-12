package cn.smartrick.metaverse.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.smartrick.metaverse.domain.dto.add.ApiLogAddDTO;
import cn.smartrick.metaverse.service.ApiLogService;
import cn.smartrick.metaverse.utils.SmartIPUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class ApiLogInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiLogService apiLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){return true;}
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String remoteIp = SmartIPUtil.getRemoteIp(request);
        String userAgent = request.getHeader("User-Agent");
        String fingerprint = request.getHeader("Fingerprint");
        String platform = "未知";
        if (StrUtil.isNotBlank(userAgent)) {
            UserAgent parse = UserAgentUtil.parse(userAgent);
            platform = parse.getPlatform().getName();
        }

        Api api = handlerMethod.getBeanType().getAnnotation(Api.class);
        ApiOperation apiOperation = handlerMethod.getMethodAnnotation(ApiOperation.class);
        if (api != null && apiOperation != null) {
            ApiLogAddDTO apiLogAddDTO = new ApiLogAddDTO();
            apiLogAddDTO.setPlatform(platform);
            apiLogAddDTO.setRemoteAddr(remoteIp);
            apiLogAddDTO.setClientUuid(fingerprint);
            apiLogAddDTO.setReqTime(new Date());
            apiLogAddDTO.setModule(api.value());
            apiLogAddDTO.setApiName(apiOperation.value());
            apiLogAddDTO.setApiPath(request.getRequestURI());
            apiLogService.add(apiLogAddDTO);
        }
        return true;
    }

}
