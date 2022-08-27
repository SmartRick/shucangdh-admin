//package cn.smartrick.metaverse.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Enumeration;
//
//@Aspect
//@Component
//public class ApiLogAspect {
//    @Autowired
//    private HttpServletRequest request;
//
//    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
//    private void apiLogRecord(){}
//
//    @After("apiLogRecord()")
//    public void apiLogRecordHandle(JoinPoint point){
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while(headerNames.hasMoreElements()){
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName +"="+request.getHeader(headerName));
//        }
//        Signature signature = point.getSignature();
//        System.out.println(signature.getDeclaringType().getSimpleName());
//    }
//
//}
