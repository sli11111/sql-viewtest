package com.ysmjjsy.goya.BasketBall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;//姓名
    private String weizhi;//场上位置
    private RightRate rightRate;//命中率
    private int power;//体力
    private CompareChoose choose;
    private String zhenYing;
    private String qiuQuan;

}
