package com.ysmjjsy.goya.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用来字符中查找占位符的内容
 *
 * @author cxc
 * @date 2018/11/23 10:44
 */
public class OffestUtil {
    /**
     * 获取占位符中的内容
     *
     * @param str
     * @return
     */
    public static List<String> match(String str) {
        List<String> results = new ArrayList<String>();
        Pattern p = Pattern.compile("\\{([\\w]*)\\}");
        Matcher m = p.matcher(str);
        while (m.find()) {
            results.add(m.group(1));
        }
        return results;
    }

    /**
     * 将String中需要填充的占位符 用Map填充
     *
     * @return
     */
    public static String fullStringByMap(String str, Map map) {
        String res = str;
        List<String> list = match(str);
        for (String data : list) {
            res = res.replace("{" + data + "}", (String) map.get(data));
        }
        return res;
    }


    //测试
    public static void main(String[] args) {
        String req = "adfwe{abc}defg{def}gju{ght}dfdf";
        List<String> list = match(req);
        for (String res : list) {
            System.out.println(res);
        }
    }
}
