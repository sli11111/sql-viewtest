package com.ysmjjsy.goya.test;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import cn.hutool.db.DbUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import com.sun.media.jfxmediaimpl.MediaUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

public class TableToExcel {

    public static String filePath="e:\\\\test\\\\db8.xlsx";
    public static void main(String[] args) {

        createExcel();
    }



    public static void createExcel(){

        //获取所有表
        List<String> tables = MetaUtil.getTables(DbUtil.getDs());
        File file=new File(filePath);
        if(file.exists() && file.isFile()) {
            file.delete();
        }


        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(filePath);

        //获取所有目录
        try {
            String catalog = MetaUtil.getCatalog(DbUtil.getDs().getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //writer.renameSheet("目录");
        //writer.getWorkbook().getCreationHelper().createHyperlink(type)
        for(String tableName:tables) {
            //获取具体的表信息
            Table table = MetaUtil.getTableMeta(DbUtil.getDs(), tableName);
            //切换sheet，此时从第0行开始写
            if(table.getComment()!=null && !table.getComment().trim().equals("")) {
                writer.setSheet(table.getComment());
            }else {
                writer.setSheet(tableName);
            }

            //行中对齐
            writer.getStyleSet().setAlign(HorizontalAlignment.LEFT, VerticalAlignment.CENTER);


            //合并单元格后的标题行，使用默认标题样式
//            writer.merge(MetaUtil.get, "表名："+tableName+"("+table.getComment()+")");

            //自定义标题别名
            writer.getHeadCellStyle().setAlignment(HorizontalAlignment.CENTER);
            writer.addHeaderAlias("colId", "序号");
            writer.setColumnWidth(0, 6);
            writer.addHeaderAlias("colName", "字段名");
            writer.setColumnWidth(1, 30);
            writer.addHeaderAlias("colType", "类型(长度)");
            writer.setColumnWidth(2, 30);
            writer.addHeaderAlias("isNullable", "是否可空");
            writer.setColumnWidth(3, 10);
            writer.addHeaderAlias("colDefault", "默认值");
            writer.setColumnWidth(4, 10);
            writer.addHeaderAlias("colComment", "备注");
            writer.setColumnWidth(5, 50);

            Collection<Column> rows = table.getColumns();

            //当前行高
            writer.setRowHeight(-1, 18);

            //写出内容，使用默认样式
            writer.write(rows);
        }
        // 关闭writer，释放内存
        writer.close();
    }


    /**创建目录
     * @param writer
     * @param tableMap
     */

    /**
     * 创建超链接
     * @param writer
     * @param x
     * @param y
     * @param linkCellValue
     * @param linkAddress
     * @param hyperlink
     */
    public static void createHyperlink(ExcelWriter writer,
                                       int x,int y,
                                       String linkCellValue,
                                       String linkAddress,
                                       Hyperlink hyperlink) {
        Cell likeCell=writer.getCell(x, y, true);
        // "#"表示本文档    "明细页面"表示sheet页名称  "A10"表示第几列第几行
        hyperlink.setAddress(linkAddress);
        likeCell.setHyperlink(hyperlink);
        // 点击进行跳转
        likeCell.setCellValue(linkCellValue);

        /* 设置为超链接的样式*/
        CellStyle linkStyle = writer.getWorkbook().createCellStyle();
        Font cellFont=writer.createFont();

        cellFont.setUnderline((byte) 1);
        cellFont.setColor(HSSFColorPredefined.BLUE.getIndex());
        linkStyle.setFont(cellFont);
        likeCell.setCellStyle(linkStyle);
    }



}
