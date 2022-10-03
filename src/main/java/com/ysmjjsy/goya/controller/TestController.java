package com.ysmjjsy.goya.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;


public class TestController {
/**
 * 对html的规范要求极高,例如：页面中<mate></mate>必须闭合，必须: <br />
 *
 * <pre>
 * <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 * "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> <html
 * xmlns="http://www.w3.org/1999/xhtml">
 *
 * @author zsj
 *
 */
public static void main(String[] args) throws Exception {
    com.itextpdf.text.Document doc = new Document();
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
