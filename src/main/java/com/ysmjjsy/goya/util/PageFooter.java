package com.ysmjjsy.goya.util;


import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

//页脚事件
public class PageFooter extends PdfPageEventHelper {
    public static PdfPTable footer;

    private  int rowStart;
    private  int rowEnd;
    private  float xPos;
    private  float yPos;
    /***
     * 设置位置 范围
     * @param rowStart
     * @param rowEnd
     * @param xPos
     * @param yPos
     */
    public void setTableRange(int rowStart,  int rowEnd,  float xPos,  float yPos) {
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @SuppressWarnings("static-access")
    public PageFooter(PdfPTable footer) {
        this.footer = footer;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        //把页脚表格定位
        footer.writeSelectedRows(this.rowStart, this.rowEnd, this.xPos, this.yPos, writer.getDirectContent());
    }

    /***
     * 页脚是图片
     * @param writer
     * @param imageAddress 图片文件地址
     * @throws MalformedURLException
     * @throws IOException
     * @throws DocumentException
     */
    public void setTableFooter(PdfWriter writer,String imageAddress) throws MalformedURLException, IOException, DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(523);
        PdfPCell cell = new PdfPCell();
        cell.setBorder(1);
        Image image01;
        image01 = Image.getInstance(imageAddress); //图片自己传
        image01.scaleAbsoluteWidth(523);
        image01.scaleAbsoluteHeight(30f);
        image01.setWidthPercentage(100);
        cell.addElement(image01);
        table.addCell(cell);
        PageFooter event = new PageFooter(table);
        writer.setPageEvent(event);
    }

    /***
     * 页脚是文字
     * @param writer
     * @param font
     * @param title
     */
    public void setTableFooter(PdfWriter writer, Font font,String title) {
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(520f);
        PdfPCell cell = new PdfPCell();
        cell.setBorder(1);
        Paragraph p = new Paragraph(title, font);
        cell.setPaddingLeft(10f);
        cell.setPaddingTop(-2f);
        cell.addElement(p);
        table.addCell(cell);
        PageFooter event = new PageFooter(table);
        writer.setPageEvent(event);
    }
}