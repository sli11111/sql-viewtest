package com.ysmjjsy.goya.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.net.multipart.UploadFile;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.word.Word07Writer;
import com.ysmjjsy.goya.entity.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
     * hutool上传接口
     */
    @GetMapping("/uploadFile")
    public void uploadFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        Console.log("show info:{}", originalFilename);
        String uploadPath = FileUtil.getWebRoot().getPath()+FileUtil.FILE_SEPARATOR+"src"+
                FileUtil.FILE_SEPARATOR+"main"+FileUtil.FILE_SEPARATOR+"resources"+
                FileUtil.FILE_SEPARATOR+"upload"+FileUtil.FILE_SEPARATOR+ IdUtil.getSnowflakeNextIdStr()+"."+FileUtil.getSuffix(originalFilename);
        Console.log("show info:{}", uploadPath);
        FileUtil.writeBytes(file.getBytes(), uploadPath);

    }
    /**
     * 压缩包接口
     */
    @GetMapping("/zip")
    public void zip(HttpServletResponse response) throws IOException {
        File file = ZipUtil.zip(new File("D:\\gitProject\\demo\\0620\\sql-viewtest\\src\\main\\resources\\upload"));
        Console.log("show info:{}", file.getPath());
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(FileUtil.readBytes(file));
        outputStream.flush();
        outputStream.close();
    }

    //



}
