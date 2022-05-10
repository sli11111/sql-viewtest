package com.ysmjjsy.goya.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ysmjjsy.goya.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author goya
 * @create 2022-03-18 21:04
 */
@Data
@ApiModel(value="用户", description="用户信息")
@EqualsAndHashCode(callSuper = true)
@TableName("T_USER")
public class User extends BaseEntity {

    @NotNull(message = "用户姓名不能为空")
    @ApiModelProperty(value = "用户姓名")
    @TableField("USER_NAME")
    private String userName;

    @NotNull(message = "用户性别不能为空")
    @ApiModelProperty(value = "用户性别")
    @TableField("GENDER")
    private Integer gender;

    @NotNull(message = "用户年龄不能为空")
    @ApiModelProperty(value = "用户年龄")
    @TableField("AGE")
    private Integer age;

    @NotNull(message = "用户邮箱不能为空")
    @ApiModelProperty(value = "用户邮箱")
    @TableField("EMAIL")
    private String email;

    @NotNull(message = "用户手机号不能为空")
    @ApiModelProperty(value = "用户手机号")
    @TableField("TELPHONE")
    private String telphone;

    @NotNull(message = "用户身份证号不能为空")
    @ApiModelProperty(value = "身份证号")
    @TableField("ID_NUMBER")
    private Integer idNumber;
}
