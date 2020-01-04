package com.xr.hongqi.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * IAE_Extract  提货表
 */
@Data
public class IaeExtract implements Serializable {


    private static final long serialVersionUID = 6870375608640832642L;
    private Integer id; //出港单位
    private String vehicleint;//航班车次
    private String transportWay;//运输方式
    private String waybillID;//货票号
    private Date estimateDate;//预计到港时间
    private Integer cargoN;//件数
    private String importantHints;//重要提示

}
