package cn.smartrick.metaverse.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动维护对象创建时间和更新时间
 * 在字段上加 @TableField(fill = FieldFill.*) 注解后，创建对象或更新对象时都会执行该Hook类中的insertFill或updateFill方法
 * 可以自动填充字段值（也可以主动设置，自动填充就会不会生效）
 */
@Slf4j
@Component
public class MetaEntityHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //strict*Fill严谨的填充策略，保证原字段为空才填充，否则不填充
        this.strictInsertFill(metaObject, "createdAt", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updatedAt", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
//        this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
//        this.strictUpdateFill(metaObject, "updatedAt", Date.class, new Date()); // 起始版本 3.3.0(推荐)
//        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
        this.setFieldValByName("updatedAt", new Date(), metaObject);
    }
}
