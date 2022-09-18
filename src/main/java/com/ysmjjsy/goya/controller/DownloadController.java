package com.ysmjjsy.goya.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.net.multipart.UploadFile;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.word.Word07Writer;
import com.ysmjjsy.goya.entity.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/downLoad")
@Api(tags = "下载接口")
public class DownloadController {


    @GetMapping("/downloadword")
        public void writeTest(HttpServletResponse response) throws IOException {
            Word07Writer writer = new Word07Writer();
            writer.addText(new Font("方正小标宋简体", Font.PLAIN, 22), "我是第一部分", "我是第二部分");
            writer.addText(new Font("宋体", Font.PLAIN, 22), "我是正文第一部分", "我是正文第二部分");
           ServletOutputStream outputStream = response.getOutputStream();
           writer.flush(outputStream);
            writer.close();
            Console.log("OK");

    }
    /**
     * 导出pdf
     */
    public void expordPdf(HttpServletResponse response){

    }

}
