package com.ysmjjsy.goya.util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * 设置页面附加属性
 *
 * @author hongli.zhang
 * @create 2020/5/18 21:00
 **/
@Component
public class PDFBuilder extends PdfPageEventHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PDFBuilder.class);
    String uploadPath = this.getClass().getClassLoader().getResource("").getPath() + File.separator;
    String fontPath = uploadPath + File.separator + "templates/msyh.ttf";
    public Phrase header;

    public Phrase footer;

    // 模板
    public PdfTemplate total;

    // 基础字体对象
    public BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);;

    // 利用基础字体生成的字体对象，一般用于生成中文文字
    public Font fontDetail = null;

    public PDFBuilder() throws DocumentException, IOException {

    }

    public PDFBuilder(Phrase header, Phrase footer) throws DocumentException, IOException {
        this.header = header;
        this.footer = footer;
    }

    public void setHeader(Phrase header) {
        this.header = header;
    }

    public void setFooter(Phrase footer) {
        this.footer = footer;
    }

    /**
     * 文档打开时创建模板
     *
     * @param writer
     * @param document
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(50, 50);// 共 页 的矩形的长宽高
    }

    /**
     * 关闭每页的时候，写入页眉，页脚。
     *
     * @param writer
     * @param document
     */
    public void onEndPage(PdfWriter writer, Document document) {
        this.addPage(writer, document);
//        this.addWatermark(writer);
    }

    public static void setImg(PdfWriter writer, String path, float fitWidth, float fitHeight, float absoluteX, float absoluteY) {
        try {
            Image image = Image.getInstance(path);
            image.setAlignment(Image.MIDDLE);
            image.scaleToFit(fitWidth, fitHeight);
            image.setAbsolutePosition(absoluteX, absoluteY);
            writer.getDirectContent().addImage(image);
        } catch (Exception e) {
            LOGGER.error("[ERROR] Set Img : img file does not exist.");
        }
    }

    //加分页
    public void addPage(PdfWriter writer, Document document) {

//        String fontPath = "D:\\gitProject\\demo\\0620\\sql-viewtest\\src\\main\\resources\\templates\\msyh.ttf";
        if (document.getPageNumber() > 1) {
            try {
                bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BaseFont blodf = null;
            try {
                blodf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            fontDetail = new Font(bf, 9, Font.NORMAL);// 数据体字体
            Font titleFnot = new Font(blodf, 10.5f, Font.NORMAL);
            Font blodFnot = new Font(blodf, 9f, Font.NORMAL);
            Font font = new Font(bf, 10.5f, Font.NORMAL);

            setImg(writer, "E:\\image3\\程序猿专属壁纸\\plief.jpg", 85, 45, 65, 785);
            Phrase pageNumberPh = new Phrase(String.valueOf(document.getPageNumber() - 1), fontDetail);
            float center = document.getPageSize().getRight() / 2;//页面的水平中点
            float left = document.getPageSize().getLeft(90);//页面的z左边距
            float headerleft = document.getPageSize().getLeft(180);//页面的z左边距
            float right = document.getPageSize().getRight(90);//页面的z左边距
            float top = document.getPageSize().getTop() - 36;
            float bottom = document.getPageSize().getBottom() + 36;

            header = new Paragraph("页眉", titleFnot);
            // 2.写入前半部分的 第 X页/共
            int pageS = writer.getPageNumber();
            String foot1 = "第 " + pageS + " 页/共";
            Phrase footer = new Phrase(foot1, fontDetail);

            // 3.计算前半部分的foot1的长度，后面好定位最后一部分的'Y页'这俩字的x轴坐标，字体长度也要计算进去 = len
            float len = bf.getWidthPoint(foot1, 9);

            // 4.拿到当前的PdfContentByte
            PdfContentByte cb = writer.getDirectContent();

            // 5.写入页脚1，x轴就是(右margin+左margin + right() -left()- len)/2.0F
            // ,y轴就是底边界-20,否则就贴边重叠到数据体里了就不是页脚了；注意Y轴是从下往上累加的，最上方的Top值是大于Bottom好几百开外的。
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,(document.rightMargin() + document.right() + document.leftMargin() - document.left() - len) / 2.0F + 20F,document.bottom() - 20, 0);

            // 6.写入页脚2的模板（就是页脚的Y页这俩字）添加到文档中，计算模板的和Y轴,X=(右边界-左边界 - 前半部分的len值)/2.0F +
            // len ， y 轴和之前的保持一致，底边界-20
            cb.addTemplate(total,(document.rightMargin() + document.right() + document.leftMargin() - document.left()) / 2.0F + 20F,document.bottom() - 20);
            // 调节模版显示的位置

//          howTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, footer7, left, bottom, 0); //页脚
////          页脚
         // ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, footer8, left, bottom - 10, 0); //页脚
           // ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, footer9, center, bottom - 20, 0); //页脚
        }
    }

    //加水印
    public void addWatermark(PdfWriter writer) {
        // 水印图片
        Image image;
        try {
            image = Image.getInstance("");
            PdfContentByte content = writer.getDirectContentUnder();
            content.beginText();
            // 开始写入水印
            for (int k = 0; k < 5; k++) {
                for (int j = 0; j < 4; j++) {
                    image.setAbsolutePosition(150 * j, 170 * k);
                    content.addImage(image);
                }
            }
            content.endText();
        } catch (IOException | DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     *
     *
     * TODO 关闭文档时，替换模板，完成整个页眉页脚组件
     * @see PdfPageEventHelper#onCloseDocument(PdfWriter,
     *      Document)
     */


    public void onCloseDocument(PdfWriter writer, Document document) {
        // 7.最后一步了，就是关闭文档的时候，将模板替换成实际的 Y 值,至此，page x of y 制作完毕，完美兼容各种文档size。

        ChineseFontUtil chineseFontUtil = new ChineseFontUtil();
        total.beginText();
        total.setFontAndSize(bf,9);// 生成的模版的字体、颜色
        String foot9 = "" + (writer.getPageNumber()-1) + " 页"; //页脚内容拼接  如  第1页/共2页
        total.showText(foot9);// 模版显示的内容
        total.endText();
        total.closePath();
    }
}

