package com.nanjing.juc.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yimo
 * @version 1.0
 * @date 2022/3/20 20:47
 */
@Data
@AllArgsConstructor //生成有参构造
@NoArgsConstructor  //生成无参构造
public class GuliException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;//异常信息

    @Override
    public String toString() {
        return "GuliException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }

}
