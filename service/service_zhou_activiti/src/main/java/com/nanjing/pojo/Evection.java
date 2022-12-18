package com.nanjing.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：楼兰
 * @date ：Created in 2021/4/8
 * @description:
 **/
@Data
public class Evection implements Serializable {

    /**
     * 主键Id
     */
    private Long id;
    /**
     * 出差单的名字
     */
    private String evectionName;
    /**
     * 出差天数
     */
    private Double num;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 出差结束时间
     */
    private Date endDate;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 出差原因
     */
    private String reson;


}
