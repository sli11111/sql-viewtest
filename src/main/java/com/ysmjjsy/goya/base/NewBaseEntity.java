package com.ysmjjsy.goya.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author goya
 * @create 2022-03-18 22:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class NewBaseEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建人姓名(用户姓名)")
    @TableField(value = "CREATE_BY_NAME", fill = FieldFill.INSERT)
    private String createByName;

    @ApiModelProperty(value = "更新人姓名(用户姓名)")
    @TableField(value = "UPDATE_BY_NAME", fill = FieldFill.INSERT_UPDATE)
    private String updateByName;

}
