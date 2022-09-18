package com.ysmjjsy.goya.test;

import cn.hutool.core.lang.Console;
import cn.hutool.system.SystemUtil;

import java.lang.management.GarbageCollectorMXBean;
import java.util.List;

public class SystemUtilTest {
    public String getPath(){
        String path1 = this.getClass().getClassLoader().getResource("").getPath();
        return path1;
    }
    
    public static void main(String[] args) {
        
        int i = SystemUtil.getTotalThreadCount();
        Console.log("show info:{}", i);
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = SystemUtil.getGarbageCollectorMXBeans();
        Console.log("show info:{}", garbageCollectorMXBeans);


    }
    
}
