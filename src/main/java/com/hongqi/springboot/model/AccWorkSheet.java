package com.hongqi.springboot.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccWorkSheet implements Serializable {
    private static final long serialVersionUID = -3617589473124428235L;
    private Integer id;//编号
    private String workSheetNo;//工作单号
    private String destination;//到达地
    private String productTime;//产品时限
    private int total;//总件数
    private double weight;
    private String stowageRequirements;//配载要求

    /**
     * 新增
     */
    private String businessNoticeNo;//业务通知单号
    private String customCode;
    private String production;
    private String simpleCode;
    private String arriveCity;
    private String pickupAddress;
    private String linkman;
    private String empNo;
    private String reservationTime;
    private String sendMan;
    private String customName;
    private String name;
    private String disabled;//是否禁止，1是，0否




}