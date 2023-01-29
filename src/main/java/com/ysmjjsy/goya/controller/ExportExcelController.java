package com.ysmjjsy.goya.controller;
import jdk.nashorn.internal.ir.VarNode;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/exportExcel")
public class ExportExcelController {
    /**
     * 基础版导出
     * @param response
     * @throws IOException
     */
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = null;
        response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");
        try {
            outputStream = response.getOutputStream();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("test");
            for (int i = 0; i < 100;i++) {
                XSSFRow row = sheet.createRow(i);
                for (int j = 0; j < 10; j++){
                    XSSFCell cell = row.createCell((short)j);
                    row.getCell((short)j).setCellValue("test");

                }
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (outputStream!=null) {
                outputStream.close();
            }
        }

    }
    /**
     * 如何创建日期单元格
     */
    @GetMapping("/createDateCell")
    public void createDateCell(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = null;
        try {
            response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");
            outputStream = response.getOutputStream();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFCreationHelper creationHelper = wb.getCreationHelper();
            XSSFSheet sheet = wb.createSheet("test2");
            for (int i = 0; i < 100;i++) {
                XSSFRow row = sheet.createRow(i);
                for (int j = 0; j < 2; j++) {
                    XSSFCell cell = row.createCell((short) j);
                    if (j==0) {
                        cell.setCellValue(new Date());
                    }else {
                        XSSFCellStyle cellStyle = wb.createCellStyle();
                        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));
                        cell.setCellValue(new Date());
                        cell.setCellStyle(cellStyle);
                    }
                }
            }
            wb.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (outputStream!=null){
                outputStream.close();
            }
        }


    }
    /**
     * 使用不同类型的单元格
     */
    @GetMapping("typeCell")
    public void typeCell(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = null;
        try {
            outputStream=response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("test");
            for (int i = 0; i < 10;i++) {
                switch (i) {
                    case 0:
                        XSSFRow row = sheet.createRow(i);
                        XSSFCell cell = row.createCell(i);
                        cell.setCellValue(1.1f);
                        break;
                    case 1 :
                        XSSFRow row1 = sheet.createRow(i);
                        XSSFCell cell1 = row1.createCell(i);
                        cell1.setCellValue("test");
                        break;
                    case 2 :
                        XSSFRow row2 = sheet.createRow(i);
                        XSSFCell cell2 = row2.createCell(i);
                        cell2.setCellValue(3);
                        break;
                    case 3 :
                        XSSFRow row3 = sheet.createRow(i);
                        XSSFCell cell3 = row3.createCell(i);
                        cell3.setCellValue(Calendar.getInstance());
                        break;
                    case 4 :
                        XSSFRow row4 = sheet.createRow(i);
                        XSSFCell cell4 = row4.createCell(i);
                        cell4.setCellValue(true);
                        break;
                    default:
                        XSSFRow row5 = sheet.createRow(i);
                        XSSFCell cell5 = row5.createCell(i);
                        cell5.setCellType(CellType.ERROR);
                        break;
                }
            }
            wb.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (outputStream!=null){
                outputStream.close();
            }
        }

    }
    /**
     * 在行和单元格上的迭代
     * 在某些情况下,在迭代时,您需要完全控制如何处理缺失或空白行和单元格,
     * 并且您需要确保访问每个单元格,而不仅仅是文件中定义的单元格。
     * (CellIterator只会返回文件中定义的单元格,这主要是具有值或样式的单元格,但它取决于Excel)。
     * 在这些情况下,您应该获取行的第一列和最后一列信息,然后调用getCell(int,MissingCellPolicy)来获取单元格。
     * 使用MissingCellPolicy控制如何处理空白或空单元格。
     */
    @GetMapping("/itreator")
    public void itreator(HttpServletResponse response){

    }
    /**
     * 7、获取单元格内容
     * 要获取单元格的内容,你首先需要知道它是什么样的单元格(例如,要求字符串单元格的数字内容会得到一个NumberFormatException)。
     *  因此,您将需要打开单元格的类型,然后为该单元格调用适当的getter。
     *
     * 在下面的代码中,我们循环遍历每个单元格,打印出单元格的引用(例如A3),然后打印单元格的内容。
     */
    @GetMapping("/getCellContent")
    public void getCellContent(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook("D:\\gitProject\\demo\\0620\\sql-viewtest\\src\\main\\resources\\templates\\test.xlsx");
        XSSFSheet sheet = workbook.getSheetAt(1);
        //通过获取单元格值并应用任何数据格式(日期,0.00,1.23e9,$ 1.23等)获取单元格中显示的文本。
        DataFormatter formatter = new DataFormatter();
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j=0;j<row.getLastCellNum();j++){
                XSSFCell cell = row.getCell(j);
                String s = formatter.formatCellValue(cell);
                System.out.println(s);
            }
        }


    }
    /**
     * 文本提取
     *    对于大多数文本提取需求,标准ExcelExtractor类应该提供了您所需要的。
     *
     */
    @GetMapping("/excelExtractorFile")
    public void excelExtractorFile(HttpServletResponse response) throws IOException {
//        InputStream inp = Files.newInputStream(Paths.get("D:\\gitProject\\demo\\0620\\sql-viewtest\\src\\main\\resources\\templates\\test.xlsx"));
//        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
//        ExcelExtractor extractor = new ExcelExtractor(wb);
//        extractor.setFormulasNotResults(true);
//        extractor.setIncludeSheetNames(false);
//        String text = extractor.getText();
//        System.out.println(text);

    }
    /**
     * 对齐单元格
     *
     */
    @GetMapping("/cellDuiQi")
    public void DuiQi(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");
        ServletOutputStream outputStream = null;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            outputStream = response.getOutputStream();
            XSSFSheet sheet = workbook.createSheet("test");
            for (int i = 0; i < 3;i++) {
                XSSFRow row = sheet.createRow(i);
                XSSFCell cell = row.createCell(i);
                cell.setCellValue("test");
                XSSFCellStyle cellStyle = workbook.createCellStyle();
                if (i==0) {
                    cellStyle.setAlignment(HorizontalAlignment.LEFT);
                    cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                    //设置边框
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBottomBorderColor(IndexedColors.YELLOW.getIndex());
                    //设置前景色
                    cellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cell.setCellStyle(cellStyle);
                }else if(i==1){
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle.setBorderLeft(BorderStyle.SLANTED_DASH_DOT);
                    cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                    cellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cell.setCellStyle(cellStyle);
                }else {
                    cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                    cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
                    cellStyle.setBorderTop(BorderStyle.DASHED);
                    cellStyle.setBottomBorderColor(IndexedColors.GREEN.getIndex());
                    cellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cell.setCellStyle(cellStyle);
                }

            }
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (outputStream!=null){
                outputStream.close();
            }
        }


    }


    /**
     * 设置页脚
     */
    @GetMapping("/setPageOfFooter")
    public void setPageOfFooter(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            XSSFWorkbook workbook = new XSSFWorkbook();
            response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");
            XSSFSheet sheet = workbook.createSheet("test");
            Footer footer = sheet.getFooter();
            footer.setRight(HeaderFooter.page()+"/"+HeaderFooter.numPages());
            //缩放倍率
            sheet.setZoom(75);
            for (int i = 0; i < 100;i++) {
                XSSFRow row = sheet.createRow(i);
                for (int j = 0; j < 10; j++) {
                    XSSFCell cell = row.createCell((short) j);
                    cell.setCellValue("test");
                }
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (outputStream!=null){
                outputStream.close();
            }
        }


    }
    /**
     * 数据验证(下拉框)
     */
    @GetMapping("/dataValiDation")
    public void DataValidation(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream =null;
        try {
            outputStream =  response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("test");
            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
            XSSFDataValidationConstraint dvConstraint =(XSSFDataValidationConstraint)
                    dvHelper.createExplicitListConstraint(new String [] {"11","21","31"});
            CellRangeAddressList addressList = new CellRangeAddressList(0,0,0,0);
            XSSFDataValidation validation =(XSSFDataValidation)dvHelper.createValidation(
                    dvConstraint,addressList);
            validation.setShowErrorBox(true);
            sheet.addValidationData(validation);
//            注意,对setSuppressDropDowmArrow()方法的调用可以简单地排除或替换为:
            validation.setSuppressDropDownArrow(true);
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (outputStream!=null){
                outputStream.close();
            }
        }
    }
    /**
     * 要获得将检查输入值的验证,例如,为10到100之间的整数
     */
    @GetMapping("validationNum")
    public void ValidationNum(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook("D:\\gitProject\\demo\\0620\\sql-viewtest\\src\\main\\resources\\templates\\test.xlsx");
        XSSFSheet sheet = workbook.getSheetAt(1);
        for (int i = 0; i < sheet.getLastRowNum();i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {

            }

        }
    }

}


