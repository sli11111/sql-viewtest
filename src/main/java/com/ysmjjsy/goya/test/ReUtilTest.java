package com.ysmjjsy.goya.test;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;

import java.util.List;

public class ReUtilTest {
    public static void main(String[] args) {
        String s = "sdgkjs;ohjrweihojwrohijmkonmb3465474568ghfj;lmdd;lnmfd4w574";
        String s1 = "aaa444中国为敌——546437-;h4365471;eg";
        List<String> all = ReUtil.findAll("\\d+", s, 0);
        Console.log("show info:{}", all);
        List<String> all1 = ReUtil.findAll("[^;]", s1, 0);
        Console.log("show info:{}", all1);

    }
}
