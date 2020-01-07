package com.hongqi.springboot.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * IAE_Arrival  进港表
 */
@Data
public class IaeArrival implements Serializable {

    private static final long serialVersionUID = 815719003485368763L;
    private Integer id;//工作单号 --自动递增
    private String sendCompany;//派送单位
    private String cargoAddress;//货物地址
    private String issuePerson;//分单人
    private Date issueDate;//分单时间
    private String workAddress;//入库运转中心
    private Date storageDate;//入库时间
    private Date outBoundDate;//出库时间
    private Date timeLimit;//送达时限
    private Date batch;//进港分拨批次


}
