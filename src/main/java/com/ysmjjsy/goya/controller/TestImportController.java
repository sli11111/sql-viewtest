package com.ysmjjsy.goya.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/import")
public class TestImportController {
    @GetMapping("/importExcel")
    public void loadFile(HttpServletResponse response) throws IOException {
        File file = FileUtil.file("E:\\test\\test.pdf");
        ServletOutputStream outputStream = response.getOutputStream();
        FileUtil.writeToStream(file, outputStream);
        response.setContentType("application/pdf");
        IoUtil.close(outputStream);

    }
}
