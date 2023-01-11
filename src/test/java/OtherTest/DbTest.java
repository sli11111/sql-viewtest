package OtherTest;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
//import com.ysmjjsy.goya.entity.User;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DbTest {

    @Test
    @Ignore
    public void test1() throws SQLException {
        List<Entity> test = Db.use().query("select * from  user where name = ?", "yk");
        Entity set = Entity.create("user").set("name", "yk");

        List<Entity> list = Db.use().find(set);
        Map<Object, Entity> id = list.stream().collect(Collectors.toMap(x -> x.get("id"), Function.identity()));


        Console.log("show info:{}", test);
    }


    @Test
    public void test2(){
        MapBuilder<Object, Object> map = MapUtil.builder().put("name", "yk");
        try {
            List<Entity> all = Db.use().findAll(Entity.create("user").set("name", "yk"));
            Console.log("show info:{}", all);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    }
