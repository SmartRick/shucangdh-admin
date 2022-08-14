package cn.smartrick.metaverse.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "admin-global")
public class GlobalConfig {
    private String ossBucket;
    private String ossDomain;
    private String ossAccessKey;
    private String ossSecretKey;
}
