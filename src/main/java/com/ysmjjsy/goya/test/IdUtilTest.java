package com.ysmjjsy.goya.test;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.IdUtil;

public class IdUtilTest {
    public static void main(String[] args) {
        long snowflakeNextId = IdUtil.getSnowflakeNextId();
        Console.log("show info:{}", snowflakeNextId);
        String s = DesensitizedUtil.chineseName("无锡当头有限公司");
        Console.log("show info:{}", s);

    }
}
