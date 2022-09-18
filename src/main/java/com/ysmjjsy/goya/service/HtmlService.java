package com.ysmjjsy.goya.service;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import com.itextpdf.text.Document;
import com.ysmjjsy.goya.util.ChineseFontUtil;
import com.ysmjjsy.goya.util.OffestUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * itext测试 html导出pdf
 *
 * @author cxc
 * @date 2018/11/22 23:29
 */
@Service
@Log4j2
public class HtmlService {

    @Autowired
    private ChineseFontUtil chineseFontUtil;


    /**
     * 页面直接写入输出
     */
    public void exportHtmlToPdf() {

        //创建一个临时目录接收
        String uploadPath = this.getClass().getClassLoader().getResource("").getPath() + File.separator;

        Console.log(uploadPath);

        try {
            //1、创建文档对象实例
            Document document = new Document();
            //页面大小
            Rectangle rect = new Rectangle(PageSize.B4.rotate());
            //页面背景色
            rect.setBackgroundColor(BaseColor.ORANGE);

            //创建一个pdf
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(uploadPath + "一只写Bug的猫普通的导出htmlToPdf.pdf"));
            //打开pdf
            document.open();
            //写入内容
            //模板内容
            String html = getHtml();
            ByteArrayInputStream bin = new ByteArrayInputStream(html.getBytes("UTF-8"));
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, bin, null, Charset.forName("UTF-8"), chineseFontUtil);
            //关闭pdf
            document.close();

        } catch (IOException e) {
            log.info("io异常");
        } catch (DocumentException e) {

            log.info("iText异常{}", e);
        }
    }


    /**
     * html模板导出PDF
     */
    public void exportHtmlTemplateToPdf() throws IOException {
        Console.log("开始dubugger");
        BufferedInputStream htmlTemplates = null;
        Console.log("开始dubugger");
        //创建一个临时目录接收
        String uploadPath = this.getClass().getClassLoader().getResource("").getPath() + File.separator;

        try {
            //1、创建文档对象实例
            Document document = new Document();
            //页面大小
            Rectangle rect = new Rectangle(PageSize.B4.rotate());
            //页面背景色
            rect.setBackgroundColor(BaseColor.ORANGE);

            //创建一个pdf
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(uploadPath + "一只写Bug的猫html模板导出Pdf.pdf"));
            //打开pdf
            document.open();
            //写入内容
            //模板内容
            htmlTemplates = getHtmlTemplates();
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, htmlTemplates, null, Charset.forName("UTF-8"), chineseFontUtil);

            //关闭pdf
            document.close();

        } catch (IOException e) {
            log.info("io异常");
        } catch (DocumentException e) {

            log.info("iText异常{}", e);
        } finally {
            if (htmlTemplates != null) {
                htmlTemplates.close();
            }
        }
    }

    /**
     * 页面填充内容输出PDF
     */
    public void exportHtmlFillToPdf() {

        //创建一个临时目录接收
        String uploadPath = this.getClass().getClassLoader().getResource("").getPath() + File.separator;
//        String uploadPath = FileUtil.getWebRoot().getPath();
        Console.log(uploadPath);
        try {
            //1、创建文档对象实例
            Document document = new Document();
            //页面大小
            Rectangle rect = new Rectangle(PageSize.B4.rotate());
            //页面背景色
            rect.setBackgroundColor(BaseColor.ORANGE);

            //创建一个pdf
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(uploadPath + "一只写Bug的猫普通的导出html模板Pdf.pdf"));
            //打开pdf
            document.open();
            //写入内容
            Map<String, Object> map = new HashMap<String, Object>(4);
            map.put("1", "哈哈哈");
            //模板内容
            String html = getHtmlTemplatesFill(map);
            ByteArrayInputStream bin = new ByteArrayInputStream(html.getBytes("UTF-8"));
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, bin, null, Charset.forName("UTF-8"), chineseFontUtil);
            //关闭pdf
            document.close();

        } catch (IOException | DocumentException e) {
            log.info("iText异常{}", e);
        }
    }


    /**
     * 页面填充内容输出PDF 导出到浏览器
     */
    public void exportHtmlFillPdfToBrowser(HttpServletResponse response) {
        try {
            //1、创建文档对象实例
            Document document = new Document();
            //页面大小
            Rectangle rect = new Rectangle(PageSize.A4.rotate());
            //页面背景色
            rect.setBackgroundColor(BaseColor.ORANGE);

            //创建一个pdf 输出到浏览器
            response.setContentType("application/pdf");
//            //这里表示直接返回下载
//            response.setHeader("Content-Disposition", "attachment;filename=测试.pdf");

            //生成文件
            PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
            //打开pdf
            document.open();
            //写入内容
            Map<String, Object> map = new HashMap<String, Object>(4);
            map.put("title", "南京分行数据");
            map.put("graph","请说出你想写的内容");
            //模板内容
            String html = getHtmlTemplatesFill(map);
            ByteArrayInputStream bin = new ByteArrayInputStream(html.getBytes("UTF-8"));
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, bin, null, Charset.forName("UTF-8"), chineseFontUtil);

            //关闭pdf
            document.close();

        } catch (IOException | DocumentException e) {
            log.info("iText异常{}", e);
        }
    }

    /**
     * 拼写html字符串代码
     */
    private static String getHtml() {
        StringBuffer html = new StringBuffer();
        html.append("<div>一只写Bug的猫</div>");
        html.append("<div>一只写Bug的猫</div>");
        html.append("<div>一只写Bug的猫</div>");
        html.append("<div>一只写Bug的猫</div>");
        html.append("<div><img src='http://img.chuansong.me/mmbiz_jpg/qIsbibEfba7ibBibLMkia5ia3CR6nSGMwCq70mDeasBePwdmg8G4icOMuiblKOFZlLHOric5oCnX361k0cibibfbd9K7yUQA/0?wx_fmt=jpeg'/></div>");
        return html.toString();
    }


    /**
     * 字节缓冲流读取模板文件
     */
    private BufferedInputStream getHtmlTemplates() {
        //获取文件根地址
        String uploadPath = this.getClass().getClassLoader().getResource("").getPath() + File.separator;
        BufferedInputStream bufferedInputStream = null;
        String resPath = "templates" + File.separator + "templatesToPdf.html";
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(uploadPath + resPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bufferedInputStream;
    }

    /**
     * 根据字符流读取文件 一行行填充内容
     *
     * @return
     * @throws IOException
     */
    private String getHtmlTemplatesFill(Map map) throws IOException {

        //获取文件根地址
        String uploadPath = this.getClass().getClassLoader().getResource("").getPath() + File.separator;
        BufferedReader br = null;
        StringBuffer str = new StringBuffer();

        String resPath = "templates" + File.separator + "001.html";
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(uploadPath + resPath), "UTF-8"), 60 * 1024 * 1024);
            String lineTxt;
            while ((lineTxt = br.readLine()) != null) {
                //用来获取是否有占位符的内容
                String fullString = OffestUtil.fullStringByMap(lineTxt, map);
                str.append(fullString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return str.toString();
    }


}

