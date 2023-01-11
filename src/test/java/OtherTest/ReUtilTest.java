package OtherTest;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ReUtilTest {

        @Test
        @Ignore
        public void writeTest2() {
            List<String> row = CollUtil.newArrayList("姓名", "加班日期", "下班时间", "加班时长", "餐补", "车补次数", "车补", "总计");
            BigExcelWriter overtimeWriter = ExcelUtil.getBigWriter("e:/excel/single_line.xlsx");
            overtimeWriter.write(row);
            overtimeWriter.close();
        }
    @Test
    @Ignore
    public void rePlaceTest() {
       String s = "2346365437;sdag;2436;34;6tyr;546;";
        String s1 = ReUtil.replaceAll(s,"[^;]", "$1-$3");
        Console.log("show info:{}", s1);
    }
    @Test
    @Ignore
    public void rePlaceTest2() {
        String s = "2346365437;sdag;2436;34;6tyr;546;ewg；wqeatdsaAGWQT;EAGEAA;SDHGF阿萨；";
        List<String> all = ReUtil.findAll("[^;；]+", s, 0);
        Console.log("show info:{}", all);
        

    }

    @Test
    @Ignore
    public void rePlaceTest3() {
        String s = "2346365437;sdag;2436;34;6tyr;546;ewg；wqeatdsaAGWQT;EAGEAA;SDHGF阿萨；";
        String s1 = ReUtil.replaceAll(s, "[;；]", "、");
        Console.log("show info:{}", s1);
    }

    @Test
    @Ignore
    public void delTest3() {
        String s = "2346365437;sdag;2436;34;6tyr;546;ewg；wqeatdsaAGWQT;EAGEAA;SDHGF阿萨；";
        String s1 = ReUtil.delAll("[;；]", s);
        Console.log("show info:{}", s1);
    }
    
}
