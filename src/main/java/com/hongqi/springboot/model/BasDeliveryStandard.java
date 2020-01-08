package com.hongqi.springboot.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BasDeliveryStandard implements Serializable {
    private static final long serialVersionUID = -3315204296600671943L;
    private Integer id;//编号
    private String name;//收派标准名称 唯一
    private double minWeight;//最小重量	非负数
    private double maxWeight;//非负数且必须大于最小重量
    private int operatorID;//操作人员
    private int operationUnitID;//操作单位
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date operationTime;//当前操作时间
    private int invalidateSign; //作废标志新增单据为"否"0,不可修改,作废操作时状态为"是"1

    /**
     * 收派标准
     */
    private String uname;

    private String empName;

}
