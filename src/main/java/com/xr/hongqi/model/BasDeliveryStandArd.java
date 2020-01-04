package com.xr.hongqi.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BasDeliveryStandArd implements Serializable {
    private static final long serialVersionUID = -3315204296600671943L;
    private Integer iD;//编号
    private String name;//收派标准名称 唯一
    private double minWeight;//最小重量	非负数
    private double maxWeight;//非负数且必须大于最小重量
    private int operatorID;//操作人员
    private int operationUnitID;//操作单位
    private Date operationTime;//当前操作时间

}
