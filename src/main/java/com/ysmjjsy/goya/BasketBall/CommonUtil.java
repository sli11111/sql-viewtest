package com.ysmjjsy.goya.BasketBall;

import cn.hutool.core.util.RandomUtil;

public class CommonUtil {
    //请求球权
    public static User acqiureQiuQuan(User user,int i){
            switch (i){
                case 0 :
                    user.setName("得分后卫");
                    break;
                case 1 :
                    user.setName("大前锋");
                    break;
                case 2 :
                    user.setName("小前锋");
                    break;
                case 3 :
                    user.setName("中锋");
                    break;
        }
        return user;
    }
    //综合选择
    public static CompareChoose getCompareCho(CompareChoose compareCho,User user){
        int i = RandomUtil.randomInt(0, 100);
        if (i<60){
                compareCho.setChoose("选择了传球");
            }
            if (i>=60&&i<=70){
                compareCho.setChoose("犯规了，对方罚球");
                if ("蓝方".equals(user.getQiuQuan())) {
                    user.setQiuQuan("红方");
                }else{
                    user.setQiuQuan("蓝方");
                }
            }
            if (i>=70&&i<=80){
                compareCho.setChoose("失误了，球权丢失");
            }
            if (i>=80&&i<100){
                if (i<=95) {
                    compareCho.setChoose("造成了对方犯规，我方罚球两次");
                }else {
                    compareCho.setChoose("造成了对方三分犯规，我方罚球三次");

                }
            }


        return compareCho;
    }

}
