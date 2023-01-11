package com.ysmjjsy.goya.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class Mail {
    public static void main(String[] args) {
        MailUtil.send("517122482@qq.com", "小红书入职邀请书-孟聪聪",
                "<h1>亲爱的孟聪聪</h1><br><h1>你好,</h1><br><h1>恭喜你通过小红书的面试，Red Family欢迎你的加入！</h1><h3></h3>", true,FileUtil.file("E:\\image3\\程序猿专属壁纸\\孟聪聪OFFER.pdf"));
    }

}
