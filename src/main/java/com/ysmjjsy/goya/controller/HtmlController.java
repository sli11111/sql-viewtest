package com.ysmjjsy.goya.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfEncodings;
import com.itextpdf.text.pdf.PdfWriter;
import com.ysmjjsy.goya.service.HtmlService;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author cxc
 * @date 2018/11/23 09:49
 */
@Controller
@RequestMapping("/api/html")
@ApiModel("pdf接口")
public class HtmlController {
    @Autowired
    private HtmlService htmlService;

    @GetMapping(value = "exportHtmlToPdf")
    @ResponseBody
    public String exportHtmlToPdf() {
        Console.log(""+ FileUtil.getWebRoot());
        htmlService.exportHtmlToPdf();
        return "html文件导出到pdf";
    }


    @GetMapping(value = "exportHtmlTemplateToPdf")
    @ResponseBody
    public String exportHtmlTemplateToPdf() {
        try {
            htmlService.exportHtmlTemplateToPdf();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "html模板文件导出到pdf";
    }

    /**
     * html模板填充内容到PDF
     *
     * @return
     */
    @GetMapping(value = "exportHtmlFillToPdf")
    @ResponseBody
    public String exportHtmlFillToPdf() {
        htmlService.exportHtmlFillToPdf();
        return "html模板文件导出到pdf";
    }


    /**
     * 模板直接导出到浏览器
     */
    @GetMapping(value = "/exportHtmlFillPdfToBrowser")
    public void exportHtmlFillPdfToBrowser(HttpServletResponse response)throws Exception{
        try {
            htmlService.exportHtmlFillPdfToBrowser(response);
        }catch (Exception e){
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print("导出失败");

        }
    }
    @GetMapping("/testpdf")
    public void testpdf() throws DocumentException, FileNotFoundException {
        Document doc = new Document();
//        PdfFont pdfFont = PdfFontFactory.createFont("STSongStd-Light", PdfEncodings.IDENTITY_H);
        PdfWriter.getInstance(doc, new FileOutputStream( "E:\\py\\createSamplePDF.pdf"));
        doc.open();
        Paragraph p = new Paragraph("adgdg张三");
        Font font = new Font();
        font.setSize(18);
        font.setFamily("宋体");
        p.setFont(font);
        doc.add(p);
        doc.close();






    }
}
