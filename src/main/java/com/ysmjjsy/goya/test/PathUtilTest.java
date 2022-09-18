package com.ysmjjsy.goya.test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.PathUtil;
import cn.hutool.core.lang.Console;

import java.io.File;

public class PathUtilTest {
    public static void main(String[] args) {
        String pathTest = "templates" + FileUtil.FILE_SEPARATOR + "001.html";
        String path = FileUtil.rename(FileUtil.file(pathTest), "rename.html", true).getPath();
        Console.log("show info:{}", path);

    }
}
