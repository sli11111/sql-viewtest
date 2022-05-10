package com.ysmjjsy.goya;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ysmjjsy.goya.mapper")
public class SqlViewtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlViewtestApplication.class, args);
    }

}
