package com.ysmjjsy.goya.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.FontUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.lang.Console;
import cn.hutool.core.net.multipart.UploadFile;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import cn.hutool.poi.word.Word07Writer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.deploy.net.URLEncoder;
import com.ysmjjsy.goya.base.MyExcelWriter;
import com.ysmjjsy.goya.entity.User;
import com.ysmjjsy.goya.entity.UserExport;
import io.swagger.annotations.Api;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.awt.*;
import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
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
//    @GetMapping("/uploadFile")
//    public void uploadFile(MultipartFile file) throws IOException {
//        String originalFilename = file.getOriginalFilename();
//        Console.log("show info:{}", originalFilename);
//        String uploadPath = FileUtil.getWebRoot().getPath()+FileUtil.FILE_SEPARATOR+"src"+
//                FileUtil.FILE_SEPARATOR+"main"+FileUtil.FILE_SEPARATOR+"resources"+
//                FileUtil.FILE_SEPARATOR+"upload"+FileUtil.FILE_SEPARATOR+ IdUtil.getSnowflakeNextIdStr()+"."+FileUtil.getSuffix(originalFilename);
//        Console.log("show info:{}", uploadPath);
//        FileUtil.writeBytes(file.getBytes(), uploadPath);
//
//    }
    /**
     * 压缩包接口
     */
    @GetMapping("/zip")
    public void zip(HttpServletResponse response) throws IOException {
//        File file = ZipUtil.zip(new File("D:\\gitProject\\demo\\0620\\sql-viewtest\\src\\main\\resources\\upload"));
//        Console.log("show info:{}", file.getPath());
//        ServletOutputStream outputStream = response.getOutputStream();
//        outputStream.write(FileUtil.readBytes(file));
//        outputStream.flush();
//        outputStream.close();
    }

    @GetMapping("/testpdf")
    public void testpdf() throws DocumentException, FileNotFoundException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream( "E:\\py\\createSamplePDF.pdf"));
        doc.open();
        doc.add(new Paragraph("Hello World"));
        doc.close();

    }
    /**
     导出excel 带样式
     */
    @GetMapping("exportExcelStyle")
    public void exportExcelStyle(HttpServletResponse response) throws IOException {
        ArrayList<User> users = new ArrayList<>();
        User user = null;
        for (int i = 0; i < 100000; i++) {
            user = new User();
            user.setUserName(Thread.currentThread().getName());
            user.setAge(RandomUtil.randomInt(1, 100));
            user.setEmail("21345@qq.com");
            user.setGender(1);
            user.setIdNumber(Convert.toInt(IdUtil.getSnowflakeNextId()));
            user.setId(IdUtil.randomUUID());
            user.setCreateBy("mcc");
            user.setCreateTime(new Date());
            user.setTelphone("18726317886");
            user.setUpdateBy("mcc");
            user.setUpdateTime(new Date());
            users.add(user);
        }
        List<UserExport> userExports = BeanUtil.copyToList(users, UserExport.class);
        Console.log("show info:{}", users);
        ExcelWriter writer = MyExcelWriter.getBigWriter();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset:utf-8");

        // 设置所有列为自动宽度，不考虑合并单元格
//        SXSSFSheet sheet = (SXSSFSheet) writer.getSheet();
//        sheet.trackAllColumnsForAutoSizing();
//        writer.autoSizeColumnAll();

        CellStyle cellStyle = writer.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        writer.merge(5,"用户信息表");
        writer.setOnlyAlias(true);

        DataFormat dataFormat = writer.getWorkbook().createDataFormat();
//这里的日期格式化规则与SimpleDateFormat不一样,
//部分格式可参考org.apache.poi.ss.usermodel.BuiltinFormats
        short format = dataFormat.getFormat("yyyyMMdd");
        StyleSet styleSet = writer.getStyleSet();
        styleSet.getCellStyleForDate()
                .setDataFormat(format);


        StyleSet style = writer.getStyleSet();
        org.apache.poi.ss.usermodel.Font font = writer.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 18);
        //重点，设置中文字体
        font.setFontName("宋体");
        style.getHeadCellStyle().setFont(font);
        //设置标题

        writer.write(userExports,true);
        writer.autoSizeColumnAll();

        String fileName= URLEncoder.encode("用户信息","UTF-8");
        //Content-disposition是MIME协议的扩展，MIME协议指示MIME用户代理如何显示附加的文件。
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream outputStream= response.getOutputStream();

        //将Writer刷新到OutPut
        writer.flush(outputStream,true);
        outputStream.close();
        writer.close();


    }



}
