package com.ysmjjsy.goya.util;


import java.io.File;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class PageIndex extends PdfPageEventHelper{

    public PdfTemplate total;

    public BaseFont bfChinese;

    private float documentX;
    private float documentY;

    /***
     * 设置页码坐标
     * @param documentX
     * @param documentY
     */
    public PageIndex(float documentX,float documentY) {
        this.documentX = documentX;
        this.documentY = documentY;
    }


    /**
     * 重写PdfPageEventHelper中的onOpenDocument方法
     */
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        // 得到文档的内容并为该内容新建一个模板
        total = writer.getDirectContent().createTemplate(500, 500);
        try {
            String uploadPath = this.getClass().getClassLoader().getResource("").getPath() + File.separator;
            BaseFont bfChinese;

            Font fontChinese = null;
            String prefixFont = uploadPath + File.separator + "templates/msyh.ttf";
//            String prefixFont = "/Users/jlpmainstay/Downloads/font/SIMYOU.TTF";
//            String os = System.getProperties().getProperty("os.name");
//            if(os.startsWith("win") || os.startsWith("Win")){
//                prefixFont = "C:\\Windows\\Fonts" + File.separator;
//            }else {
//                prefixFont = "/usr/share/fonts/chinese" + File.separator;
//            }

            // 设置字体对象为Windows系统默认的字体
            bfChinese = BaseFont.createFont(prefixFont, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    /**
     * 重写PdfPageEventHelper中的onEndPage方法
     */
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        // 新建获得用户页面文本和图片内容位置的对象
        PdfContentByte pdfContentByte = writer.getDirectContent();
        // 保存图形状态
        pdfContentByte.saveState();
        String text = "第"+writer.getPageNumber() + "页/共";
        // 获取点字符串的宽度
        float textSize = bfChinese.getWidthPoint(text, 9);
        pdfContentByte.beginText();
        // 设置随后的文本内容写作的字体和字号
        pdfContentByte.setFontAndSize(bfChinese, 9);

        // 定位'X/'
        float x = this.documentX;
        float y = this.documentY;
        pdfContentByte.setTextMatrix(x, y);
        pdfContentByte.showText(text);
        pdfContentByte.endText();

        // 将模板加入到内容（content）中- // 定位'Y'
        pdfContentByte.addTemplate(total, x + textSize, y);

        pdfContentByte.restoreState();
    }

    /**
     * 重写PdfPageEventHelper中的onCloseDocument方法
     */
    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        total.beginText();
        try {
            String prefixFont = "/Users/jlpmainstay/Downloads/font/SIMYOU.TTF";
            String os = System.getProperties().getProperty("os.name");
//            if(os.startsWith("win") || os.startsWith("Win")){
//                prefixFont = "C:\\Windows\\Fonts" + File.separator;
//            }else {
//                prefixFont = "/usr/share/fonts/chinese" + File.separator;
//            }

            bfChinese = BaseFont.createFont(prefixFont,BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            total.setFontAndSize(bfChinese, 9);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        total.setTextMatrix(0, 0);
        // 设置总页数的值到模板上，并应用到每个界面
        total.showText(String.valueOf(writer.getPageNumber()+"页"));
        total.endText();
    }
}
