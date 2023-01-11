package com.ysmjjsy.goya.test;

import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;

public class ImportTest {
    public static void main(String[] args) throws Exception {
         // importTest();
        showExcel();
    }
    public static void importTest() {
        String uploadPath = new ImportTest().getClass().getClassLoader().getResource("").getPath() + File.separator;
        //获取字体解决中文乱码问题
        String path = uploadPath + File.separator + "templates" + File.separator + "调度配置模板4.0.xlsx";
        for (int i = 1; i <= 12; i++) {
            ExcelUtil.readBySax(path, i, (sheetIndex, rowIndex, list) -> {
                if (sheetIndex>0&&rowIndex>1) {
                    Console.log("{}", list);
                   //入库操作
                    System.out.println("java");

                }
            });
        }

    }
    //读取数据
    public static  void showExcel() throws Exception {
        String uploadPath = new ImportTest().getClass().getClassLoader().getResource("").getPath() + File.separator;
        //获取字体解决中文乱码问题
        String path = uploadPath + File.separator + "templates" + File.separator + "调度配置模板4.0.xlsx";
        XSSFWorkbook workbook=new XSSFWorkbook(Files.newInputStream(new File(path).toPath()));
        XSSFSheet sheet=null;
        for (int i = 1; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
            sheet=workbook.getSheetAt(i);
            for (int j = 2; j < sheet.getPhysicalNumberOfRows(); j++) {//获取每行
                XSSFRow row=sheet.getRow(j);
                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {//获取每个单元格
//                    System.out.println(row.getCell(k)+"\t");
                    XSSFCell cell = row.getCell(k);
                    System.out.println(cell);

                }
            }
        }
    }

    //读取模板并向其中填写数据

}
