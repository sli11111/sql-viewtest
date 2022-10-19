package com.ysmjjsy.goya.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Text;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import com.ysmjjsy.goya.util.*;
import org.apache.poi.util.IOUtils;
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
@RequestMapping("/commons")
public class CommonsController {
    /**
     * 直接将整个文件加载到流中
     * @param response
     * @throws IOException
     */
    @GetMapping("/loadFile")
    public void loadFile(HttpServletResponse response) throws IOException {
        File file = FileUtil.file("E:\\test\\test.pdf");
        ServletOutputStream outputStream = response.getOutputStream();
        FileUtil.writeToStream(file, outputStream);
        response.setContentType("application/pdf");
        IoUtil.close(outputStream);

    }
    /**
     * 生成pdf测试itextpdf
     */
    @GetMapping("/generarotPdf")
    public void generarotPdf(HttpServletResponse response) throws DocumentException, IOException {
        //字体中文
        ChineseFontUtil chineseFontUtil = new ChineseFontUtil();
        Rectangle rectangle = new Rectangle(PageSize.A4);
        Document document =null;
        document = new Document(rectangle);
        ServletOutputStream outputStream = response.getOutputStream();
        //书写器
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        PDFBuilder pdfBuilder = new PDFBuilder();
        writer.setPageEvent(pdfBuilder);
//        PageIndex pageIndex = new PageIndex(30f,40f);
//        writer.setPageEvent(pageIndex);
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
        int[] cellsWidth = { 1, 1, 1, 1, 1, 1 };
        datatable.setWidths(cellsWidth);// 单元格宽度
        // datatable.setTotalWidth(300f);//表格的总宽度
        datatable.setWidthPercentage(100);// 表格的宽度百分比
        datatable.setPaddingTop(10.3f);
        datatable.getDefaultCell().setPadding(2);// 单元格的间隔
        datatable.getDefaultCell().setBorderWidth(1);// 边框宽度
        // 设置表格的底色
//        datatable.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
        datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPTableEvent event = new AlterNatingBackgroud();
        datatable.setTableEvent(event);
        //表格背景色
        BaseColor green = new BaseColor(175, 215, 136);
        BaseColor blue = new BaseColor(148, 170, 214);

        // PdfPTable[PdfPCell[Paragraph]]

        // 添加表格的内容
        String uploadPath = this.getClass().getClassLoader().getResource("").getPath() + File.separator;
        String fontPath = uploadPath + File.separator + "templates/msyh.ttf";
        BaseFont bf = null;
        BaseFont blodbf = null;
//        BaseFont chineseFont = null;
//        BaseFont chineseBlodFont = null;
        try {
            bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            blodbf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//            chineseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        Font coverFont = new Font(blodbf, 30, Font.NORMAL);
        Font titleFont = new Font(blodbf, 16, Font.NORMAL);
        Font coverTiletFontMarked = new Font(blodbf, 16f, Font.NORMAL, new BaseColor(148, 170, 214));
        Font textFontBold = new Font(blodbf, 10.5f, Font.NORMAL);
        Font textFont = new Font(bf, 10.5f, Font.NORMAL);
        Font textFontGray = new Font(blodbf, 10.5f, Font.NORMAL, new BaseColor(215, 215, 215));
        Font chapterFont = new Font(blodbf, 14f, Font.NORMAL);
//        Font coverFont = new Font(chineseFont, 30, Font.NORMAL);
//        Font titleFont = new Font(chineseFont, 16, Font.NORMAL);
//        Font coverTiletFontMarked = new Font(chineseFont, 16f, Font.NORMAL, new BaseColor(148, 170, 214));
//        Font textFontBold = new Font(chineseFont, 10.5f, Font.NORMAL);
//        Font textFont = new Font(chineseFont, 10.5f, Font.NORMAL);
//        Font textFontGray = new Font(chineseFont, 10.5f, Font.NORMAL, new BaseColor(215, 215, 215));
//        Font chapterFont = new Font(chineseFont, 14f, Font.NORMAL);

//        Font textFont = new Font(bf, 10.5f, Font.NORMAL);
        // 添加表头元素
        for (int i = 0; i < 6; i++) {
            datatable.addCell(getCell(new Phrase("tst"+i,textFont),blue,1,1));
        }
        for (int i = 0; i < 6; i++) {
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));
            datatable.addCell(new Paragraph("tst"+i, chineseFontUtil.getChineseFont2(10)));





















        }



        document.add(datatable);

        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
        document.close();



        IoUtil.close(outputStream);

    }

    private PdfPCell getCell(Phrase phrase, BaseColor color, int colSpan, int rowSpan) {
        PdfPCell cells = new PdfPCell(phrase);
        cells.setUseAscender(true);
        cells.setMinimumHeight(20f);
        cells.setHorizontalAlignment(Element.ALIGN_LEFT);
        cells.setVerticalAlignment(5);
        cells.setColspan(colSpan);
        cells.setRowspan(rowSpan);
        cells.setNoWrap(false);
        if (color != null) {
            cells.setBackgroundColor(color);
        }
        return cells;
    }

}
