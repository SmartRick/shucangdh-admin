package cn.smartrick.metaverse.config;

import cn.smartrick.metaverse.aspect.ApiLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Date: 2021/12/8 13:53
 * @Author: SmartRick
 * @Description: 跨域配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Autowired
    private ApiLogInterceptor apiLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLogInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 拦截所有的请求
                .allowedOrigins("*")  // 可跨域的域名，可以为 *
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*");  // 允许跨域的请求头，可以单独配置

    }
}
