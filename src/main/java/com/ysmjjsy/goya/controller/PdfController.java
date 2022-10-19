package com.ysmjjsy.goya.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.html.Div;
import com.ysmjjsy.goya.util.AlterNatingBackgroud;
import com.ysmjjsy.goya.util.ChineseFontUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class PdfController {
    @GetMapping("testPdf")
    private void testPdf() throws DocumentException, FileNotFoundException {
        ChineseFontUtil chineseFontUtil = new ChineseFontUtil();
        Rectangle rectangle = new Rectangle(PageSize.A4);
        Document document = null;
        document = new Document(rectangle);
//        ServletOutputStream outputStream = response.getOutputStream();
        //书写器
        PdfWriter.getInstance(document,new FileOutputStream("E:\\test\\testdddd.pdf"));
        //先打开文档流之后才能添加段落
        document.open();
        Paragraph p = new Paragraph("tagagjian讲解！", chineseFontUtil.getChineseFont2(20));
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(30f);
//        Paragraph p = new Paragraph();
//        p.add(new Text("你好")).add(new Text(""))
//
//        Div div = new Div();
//        div.add((IBlockElement) p);

        document.add(p);
        PdfPTable datatable = new PdfPTable(6);
        // 定义表格的宽度
        int[] cellsWidth = {1, 1, 1, 1, 1, 1};
        datatable.setWidths(cellsWidth);// 单元格宽度
        // datatable.setTotalWidth(300f);//表格的总宽度
        datatable.setWidthPercentage(100);// 表格的宽度百分比
        datatable.setPaddingTop(10.3f);
        datatable.getDefaultCell().setPadding(2);// 单元格的间隔
        datatable.getDefaultCell().setBorderWidth(1);// 边框宽度
        // 设置表格的底色
//        datatable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
//        datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPTableEvent event = new AlterNatingBackgroud();
        datatable.setTableEvent(event);
        // PdfPTable[PdfPCell[Paragraph]]
        // 添加表头元素
        for (int i = 0; i < 6; i++) {
            datatable.addCell(new Paragraph("" + i, chineseFontUtil.getChineseFont2(10)));
        }
        // 添加表格的内容
        for (int i = 0; i < 6; i++) {
            datatable.addCell(new Paragraph("tst" + i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst" + i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst" + i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst" + i, chineseFontUtil.getChineseFont2(10)));


        }

        document.add(datatable);

//        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
        document.close();


//        IoUtil.close(outputStream);




    }

    @GetMapping("generatorDirctorDir")
    public void testPdfDirectory(){
        Document document = new Document();
        document.addTitle("标题");


    }



}



