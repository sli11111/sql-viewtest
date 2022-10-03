package com.ysmjjsy.goya.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ysmjjsy.goya.util.ScopeUtil;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mulDownload")
public class MultipartFileDownloadController {
    /**
     * 从数据库中获取多个表的记录并同时下载
     */
    @GetMapping("/mulDownload")
    public void mulDownloadFile(HttpServletResponse response) {
        //获取所有表
        List<String> tables = MetaUtil.getTables(DbUtil.getDs());
        //创建excel读写
        ExcelWriter bigWriter = ExcelUtil.getBigWriter();

        //设置日期
        short i = bigWriter.getWorkbook().createDataFormat().getFormat("yyyy年MM月dd日");
        bigWriter.getStyleSet().getCellStyleForDate().setDataFormat(i);
        //设置字体
        Font font = bigWriter.createFont();
        font.setBold(true);
        font.setFontName("Microsoft YaHei");
        font.setFontHeightInPoints((short) 14);
        bigWriter.getStyleSet().getHeadCellStyle().setFont(font);
        //创建索引
        createCatalog(bigWriter, tables);
        for (String tableName : tables) {

            Table table = MetaUtil.getTableMeta(DbUtil.getDs(), tableName);
            //获取当前表的所有记录
            List<Entity> allRecords = this.findAllRecords(table.getTableName());
            //获取表结构所有列信息
            Collection<Column> columns = table.getColumns();
            HashMap<String, String> map = new HashMap<>();
            columns.forEach(c -> {
                map.put(c.getName(), c.getComment());
            });
            //添加别名
            this.addHeared(bigWriter, map);

            //切换sheet，此时从第0行开始写
            if (table.getComment() != null && !table.getComment().trim().equals("")) {
                bigWriter.setSheet(table.getComment());
                Sheet sheet = bigWriter.getSheet();
//                bigWriter.merge(0,0,0,columns.size()-1,table.getComment(),true);
//                this.addMerge(sheet);
            } else {
                bigWriter.setSheet(table.getTableName());
//                this.addMerge(bigWriter.getSheet());
            }

            //写入sheet页
            bigWriter.write(allRecords);
//行中对齐
            bigWriter.getStyleSet().setAlign(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            for (int i1 = 0; i1 < columns.size(); i1++) {
                bigWriter.setColumnWidth(i1, 30);
            }
//            bigWriter.getSheet().addMergedRegion(new CellRangeAddress(0,0,0,tables.size()-1));

        }

        response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            //将Writer刷新到OutPut
            bigWriter.flush(outputStream, true);
            SXSSFSheet sheet = (SXSSFSheet) bigWriter.getSheet();
            sheet.trackAllColumnsForAutoSizing();
            bigWriter.autoSizeColumnAll();
            outputStream.close();
            bigWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Entity> findAllRecords(String tableName) {
        List<Entity> list = null;
        try {
            list = Db.use().query("select * from " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void addHeared(ExcelWriter bigWriter, Map<String, String> map) {
        map.forEach((k, v) -> {
            bigWriter.addHeaderAlias(k, v);
        });
    }

    public static void createHyperlink(ExcelWriter writer,
                                       int x, int y,
                                       String linkCellValue,
                                       String linkAddress,
                                       Hyperlink hyperlink) {
        Cell likeCell = writer.getCell(x, y, true);
        // "#"表示本文档    "明细页面"表示sheet页名称  "A10"表示第几列第几行
        hyperlink.setAddress(linkAddress);
        likeCell.setHyperlink(hyperlink);
        // 点击进行跳转
        likeCell.setCellValue(linkCellValue);

        /* 设置为超链接的样式*/
        CellStyle linkStyle = writer.getWorkbook().createCellStyle();
        Font cellFont = writer.createFont();

        cellFont.setUnderline((byte) 1);
        cellFont.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        linkStyle.setFont(cellFont);
        likeCell.setCellStyle(linkStyle);
    }

    public static void createCatalog(ExcelWriter writer, List<String> tableMap) {
        writer.renameSheet("目录");
        //行中对齐
        writer.getStyleSet().setAlign(HorizontalAlignment.LEFT, VerticalAlignment.CENTER);

        List<Map<String, Object>> rowList = new ArrayList<>();

        //设置excel表的标题列
        String[] title = new String[]{"序号", "表名", "备注"};

        writer.setColumnWidth(0, 6);
        writer.setColumnWidth(1, 30);
        writer.setColumnWidth(2, 30);

        //当前行高
        writer.setRowHeight(-1, 18);

        int i = 1;
        for (String tableName : tableMap) {
            Map<String, Object> map = new LinkedHashMap<>();

            Table tableMeta = MetaUtil.getTableMeta(DbUtil.getDs(), tableName);
            String tableCnName = tableMeta.getComment();
            Hyperlink hyperlink = writer.getWorkbook().getCreationHelper().createHyperlink(HyperlinkType.DOCUMENT);
            String linkStr = "#" + tableName + "!A1";
            if (tableCnName != null && !tableCnName.trim().equals("")) {
                linkStr = "#" + tableCnName + "!A1";
            }

            createHyperlink(writer, 1, i, tableName, linkStr, hyperlink);
            map.put(title[0], i);
            map.put(title[1], tableName);
            map.put(title[2], tableCnName);

            rowList.add(map);
            i++;
        }
        writer.write(rowList);
    }


}
