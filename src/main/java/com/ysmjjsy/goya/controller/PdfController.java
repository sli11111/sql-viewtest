package com.ysmjjsy.goya.controller;

import cn.hutool.core.io.FileUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.html.Div;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@RestController
public class PdfController {
    @GetMapping("testPdf")
    private void testPdf(){
        Document document = new Document();
        document.addTitle("南京分行");
        Div div = new Div();
        try {
            PdfWriter.getInstance(document,new FileOutputStream("E:\\test\\testdddd.pdf"));
            document.open();
            Paragraph p = new Paragraph("test");

            Font font = new Font();
            font.setFamily("宋体");
            font.setSize(34);
            p.setFont(font);
            document.add(p);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("generatorDirctorDir")
    public void testPdfDirectory(){
        Document document = new Document();
        document.addTitle("标题");


    }


}



