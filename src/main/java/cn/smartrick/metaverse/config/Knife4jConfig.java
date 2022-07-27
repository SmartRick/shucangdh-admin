package cn.smartrick.metaverse.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

//import springfox.documentation.oas.annotations.EnableOpenApi;

//@EnableOpenApi   // 开启Swagger自定义接口文档
@EnableSwagger2WebMvc
@EnableKnife4j
@Configuration   // 相当于Spring配置中的<beans>
public class Knife4jConfig {
    @Bean(value = "defaultApi")
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("数藏系统管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.smartrick.metaverse.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("数藏系统管理API")
                .description("数藏系统管理API")
                .termsOfServiceUrl("http://www.xxxxx.com/")
                .contact(new Contact("rick",null,null))
                .version("1.0")
                .build();
    }

}
