package com.ysmjjsy.goya.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

import java.io.File;
    public class PdfFont extends XMLWorkerFontProvider {

        //新增PDF字体：特殊字符、宋体、仿宋、黑体
        public String[] fonts = { "seguisym.ttf" ,"simsun.ttc","simfang.ttf","simhei.ttf"};

        public PdfFont() {
            super(XMLWorkerFontProvider.DONTLOOKFORFONTS);
            //获取资源文件字体
            String path =  this.getClass().getClassLoader().getResource("").getPath() + File.separator;
            // 注册字体
            for (String font : fonts) {
                this.register(path+font);
            }
        }

        @Override
        public Font getFont(String fontname, String encoding, boolean embedded, float size, int style, BaseColor color) {
            //Set<String> fonts2 = this.getRegisteredFonts(); //获取注册字体
            String font = fontname;
            if(font==null){
                font = "宋体";
            }
            if ("".equals(font)) {
                font = "segoe ui symbol";// 特殊字符
            }
            if(size<=0){
                size=10.5f;
            }
            return super.getFont(font, encoding, embedded, size, style, color);
        }





}
