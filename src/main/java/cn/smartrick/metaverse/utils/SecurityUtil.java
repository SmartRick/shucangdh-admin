//package cn.smartrick.metaverse.utils;
//
//import com.ram.funculture.common.domain.SysUserDetail;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//
///**
// * @Date: 2021/12/17
// * @Author: SmartRick
// * @Description: TODO
// */
//public class SecurityUtil {
//
//    public static void saveLoginUser(Authentication authentication) throws SecurityException {
//        SecurityContext context = SecurityContextHolder.getContext();
//        if (context == null) {
//            throw new SecurityException("当前上下文为空");
//        }
//        context.setAuthentication(authentication);
//    }
//
//    public static SysUserDetail getLoginUser() throws SecurityException {
//        SecurityContext context = SecurityContextHolder.getContext();
//        if (context == null) {
//            throw new SecurityException("当前上下文为空");
//        }
//        Authentication authentication = context.getAuthentication();
//        if (authentication == null) {
//            throw new SecurityException("暂无登录记录");
//        }
//        return (SysUserDetail) authentication.getPrincipal();
//    }
//
//    public static Long getLoginUserId() throws SecurityException {
//        SysUserDetail contextUser = getLoginUser();
//        if (contextUser == null) {
//            throw new SecurityException("用户未登录");
//        }
//        return contextUser.getId();
//    }
//}
