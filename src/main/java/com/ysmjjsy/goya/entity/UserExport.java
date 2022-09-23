package com.ysmjjsy.goya.entity;

import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserExport {
    @Alias("用户名")
    private String userName;


    @Alias("性别")
    private Integer gender;


    @Alias("年龄")
    private Integer age;


    @Alias("邮箱")
    private String email;


    @Alias("电话")
    private String telphone;


    @Alias("身份证")
    private Integer idNumber;
}
