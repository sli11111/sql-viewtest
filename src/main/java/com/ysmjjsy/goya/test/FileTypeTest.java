package com.ysmjjsy.goya.test;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;

import java.io.BufferedInputStream;
import java.io.File;

public class FileTypeTest {
    public static void main(String[] args) {
        String path = FileUtil.getWebRoot().getPath()+FileUtil.FILE_SEPARATOR+"src"
                +FileUtil.FILE_SEPARATOR+"main"+FileUtil.FILE_SEPARATOR+"resources"
                +FileUtil.FILE_SEPARATOR+"templates"+FileUtil.FILE_SEPARATOR+"001.html";
        File file = FileUtil.file("D:\\gitProject\\git\\正则表达式.pdf");
        BufferedInputStream inputStream = FileUtil.getInputStream(file);
        String type = FileTypeUtil.getType(file);
        Console.log("show info:{}", type);
        String type1 = FileTypeUtil.getType(inputStream);
        Console.log("show info:{}", type1);
        String typeByPath = FileTypeUtil.getTypeByPath(path);
        Console.log("show info:{}", typeByPath);

        String html = FileTypeUtil.putFileType("ffd8ffe000104a464946", "html");
        Console.log("show info:{}", html);

    }
}
