package com.ysmjjsy.goya.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.PathUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;

import java.io.File;
import java.net.URL;
import java.util.List;

public class FileIoTest {
    public static void main(String[] args) {
        String pathTest = "templates" + FileUtil.FILE_SEPARATOR + "001.html";
        File file = FileUtil.file(pathTest);
        File webRoot = FileUtil.getWebRoot();
        Console.log("show info:{}", webRoot);
        String path = FileUtil.getWebRoot().getPath();
        Console.log("show info:{}", path);
        String userHomePath = FileUtil.getUserHomePath();
        Console.log("show info:{}", userHomePath);
        String fileSeparator = FileUtil.FILE_SEPARATOR;
        Console.log("show info:{}", fileSeparator);
        String prefix = FileUtil.getPrefix(pathTest);
        Console.log("show info:{}", prefix);
        String suffix = FileUtil.getSuffix(pathTest);
        Console.log("show info:{}", suffix);
        String suffix1 = FileUtil.getSuffix(file);
        Console.log("show info:{}", suffix1);
        String prefix1 = FileUtil.getPrefix(suffix1);
        Console.log("show info:{}", prefix1);
        File parent = FileUtil.getParent(file, 2);
        Console.log("show info:{}", parent);
        String canonicalPath = FileUtil.getCanonicalPath(file);
        Console.log("show info:{}", canonicalPath);
        List<URL> resources = ResourceUtil.getResources(path);
        Console.log("show info:{}", resources);
    }
}
