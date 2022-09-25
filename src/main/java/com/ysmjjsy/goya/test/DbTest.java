package com.ysmjjsy.goya.test;

import cn.hutool.core.lang.Console;
import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class DbTest {
    public static void main(String[] args) throws SQLException {
        List<Entity> query = Db.use().query("select * from test");
        Console.log("show info:{}", query);
        DataSource ds = DbUtil.getDs();
        Console.log("show info:{}", ds);
        List<String> tables = MetaUtil.getTables(DbUtil.getDs());
        Console.log("show info:{}", tables);
        Table test = MetaUtil.getTableMeta(DbUtil.getDs(), "test");
        Console.log("show info:{}", test.toString());
        Collection<Column> columns = test.getColumns();
        Console.log("show info:{}", columns);
        String comment = test.getComment();
        Console.log("show info:{}", comment);


        List<Entity> query1 = Db.use().query("select * from "+test.getTableName());
        Console.log("show info:{}", query1);

    }
}
