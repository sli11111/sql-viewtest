package com.ysmjjsy.goya.util;


import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

//页眉事件
public class PageHeader extends PdfPageEventHelper {
    public static PdfPTable header;
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
    public PageHeader(PdfPTable header) {
        PageHeader.header = header;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        //把页眉表格定位
        header.writeSelectedRows(this.rowStart, this.rowEnd, this.xPos, this.yPos, writer.getDirectContent());
    }

    /***
     * 设置图片页眉
     * @param writer
     * @param imageAddress 图片路径
     * @throws MalformedURLException
     * @throws IOException
     * @throws DocumentException
     */
    public void setTableHeader(PdfWriter writer,String imageAddress) throws MalformedURLException, IOException, DocumentException {
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(555);
        PdfPCell cell = new PdfPCell();
        cell.setBorder(0);
        Image image01;
        image01 = Image.getInstance(imageAddress); //图片自己传
        //image01.scaleAbsolute(355f, 10f);
        image01.setWidthPercentage(80);
        cell.setPaddingLeft(30f);
        cell.setPaddingTop(-20f);
        cell.addElement(image01);
        table.addCell(cell);
        PageHeader event = new PageHeader(table);
        writer.setPageEvent(event);
    }
}
