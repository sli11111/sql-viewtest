package com.ysmjjsy.goya.util;

import cn.hutool.core.lang.Console;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * 解决itext中文不显示
 * html版本
 *
 * @author cxc
 * @date 2018/11/23 01:39
 */
@Component
public class ChineseFontUtil  extends XMLWorkerFontProvider implements FontProvider{
    public String[] fonts = { "msyh.ttf","seguisym.ttf" ,"simsun.ttc","simfang.ttf","simhei.ttf","JDFSCHAO.TTF","仿宋_GB2312.ttf"};

    public ChineseFontUtil() {
        super(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        //获取资源文件字体
        //获取文件根地址
        String uploadPath = this.getClass().getClassLoader().getResource("").getPath() + File.separator;
        //获取字体解决中文乱码问题
        String path = uploadPath + File.separator + "templates"+File.separator;
       // String path =  this.getClass().getClassLoader().getResource("").getPath() + File.separator;
        // 注册字体
        for (String font : fonts) {
            this.register(path+font);
        }
    }

    @Override
    public Font getFont(String s,float v,  BaseColor baseColor) {
        String font = s;
        if(font==null){
            font = "宋体";
        }
        if ("".equals(font)) {
            font = "segoe ui symbol";// 特殊字符
        }
        if(v<=0){
            v=10.5f;
        }
        return super.getFont(font,  v,  baseColor);
    }


    @Override
    public boolean isRegistered(String s) {
        return false;
    }

    public Font getChineseFont() {
        //获取文件根地址
        String uploadPath = this.getClass().getClassLoader().getResource("").getPath() + File.separator;
        //获取字体解决中文乱码问题
        BaseFont baseFont = null;
        String fontPath = uploadPath + File.separator + "templates/msyh.ttf";
        try {
            baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Font(baseFont);
    }
    public Font getChineseFont2(int size) {
        //获取文件根地址
        String uploadPath = this.getClass().getClassLoader().getResource("").getPath() + File.separator;
        BaseFont bfChinese;

        Font fontChinese = null;
        String fontPath = uploadPath + File.separator + "templates/simhei.ttf";
        try {
            bfChinese = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // fontChinese = new Font(bfChinese, 12, Font.NORMAL);
            fontChinese = new Font(bfChinese, size, Font.NORMAL, BaseColor.BLUE);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fontChinese;
    }

    }
