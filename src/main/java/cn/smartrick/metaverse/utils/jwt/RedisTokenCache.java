package cn.smartrick.metaverse.utils.jwt;

import com.ram.funculture.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hu
 * @version 1.0
 * @description: TODO
 * @date 2021/9/11
 */

@Component
public class RedisTokenCache {
    @Autowired
    private RedisUtil redisUtil;

    private static final String tokenPrefix = "jwt:userId:";

    public boolean setToken(Long userId, String token) {
        return redisUtil.set(tokenPrefix + userId, token);
    }

    public String getToken(Long userId) {
        return (String) redisUtil.get(tokenPrefix + userId);
    }

    public boolean deleteToken(Long userId) {
        return redisUtil.del(tokenPrefix + userId);
    }
}
