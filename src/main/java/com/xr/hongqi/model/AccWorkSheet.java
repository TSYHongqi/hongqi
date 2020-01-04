package com.xr.hongqi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccWorkSheet implements Serializable {
    private static final long serialVersionUID = -3617589473124428235L;
    private Integer iD;//编号
    private int workSheetNo;//工作单号
    private String destination;//到达地
    private String productTime;//产品时限
    private int total;//总件数
    private double weight;
    private String stowageRequirements;//配载要求
}
