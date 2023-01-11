package com.ysmjjsy.goya.test.model;

import cn.hutool.core.io.FileUtil;
import com.ysmjjsy.goya.test.ImportTest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class ReadTemAndWrite {
    public static void main(String[] args) throws IOException {
        String uploadPath = new ImportTest().getClass().getClassLoader().getResource("").getPath() + File.separator;
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User("test"+i,i,"男"));
        }
        String path = uploadPath + File.separator + "templates" + File.separator + "test2.xlsx";
        String destPath = "E:\\test3.xlsx";
//        FileUtil.copy(path,destPath,true);
        File fi = new File(path);
        FileOutputStream fos =null;
        InputStream in = null;
        try {
            in = new FileInputStream(fi);
            XSSFWorkbook wb = new XSSFWorkbook(in);
            XSSFSheet sheet = wb.getSheetAt(0);
            fos = new FileOutputStream(destPath);
            for (int i = 0; i < users.size();i++) {
                User user = users.get(i);
                XSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(user.getName());
                row.createCell(1).setCellValue(user.getAge());
                row.createCell(2).setCellValue(user.getSex());
            }
            wb.write(fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
