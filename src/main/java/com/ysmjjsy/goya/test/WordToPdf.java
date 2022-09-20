package com.ysmjjsy.goya.test;

import com.aspose.words.Document;
import java.io.File;

public class WordToPdf {
    public static void main(String[] args) {
        try {
            //doc路径
            Document document = new Document("E:\\home\\TEST.docx");
            //pdf路径
            File outputFile = new File("E:\\home\\TEST.pdf");
            //操作文档保存
            document.save(outputFile.getAbsolutePath(), com.aspose.words.SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
