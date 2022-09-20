package com.ysmjjsy.goya.test;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;

public class IdUtilTest {
    public static void main(String[] args) {
        long snowflakeNextId = IdUtil.getSnowflakeNextId();
        Console.log("show info:{}", snowflakeNextId);


    }
}
