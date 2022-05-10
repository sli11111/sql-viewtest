package com.ysmjjsy.goya.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author goya
 * @create 2022-03-19 14:06
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {



    @Override
    public void insertFill(MetaObject metaObject) {

//        this.strictInsertFill(metaObject, "CREATE_TIME", String.class, "1997-05-22 10:10:10");
//        this.strictInsertFill(metaObject, "CREATE_BY", String.class,userService.getMap(new QueryWrapper<User>().eq("1",))); // 起始版本 3.3.0(推荐使用)
//        this.strictInsertFill(metaObject, "CREATE_BY_NAME", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        this.strictUpdateFill(metaObject, "UPDATE_TIME", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
//        this.strictUpdateFill(metaObject, "UPDATE_BY", LocalDateTime.class, LocalDateTime.now());
//        this.strictUpdateFill(metaObject, "UPDATE_BY_NAME", LocalDateTime.class, LocalDateTime.now());

    }
}
