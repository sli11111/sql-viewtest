package com.ysmjjsy.goya.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.pdf.BaseFont;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 解决itext中文不显示
 * html版本
 *
 * @author cxc
 * @date 2018/11/23 01:39
 */
@Component
public class ChineseFontUtil implements FontProvider {
    @Override
    public Font getFont(String s, String s1, boolean b, float v, int i, BaseColor baseColor) {
        return getChineseFont();
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
}
