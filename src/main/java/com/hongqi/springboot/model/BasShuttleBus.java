package com.hongqi.springboot.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BasShuttleBus implements Serializable {
    private static final long serialVersionUID = -4706952117194781709L;
    private Integer iD;//编号
    private int lineType;//线路类型 1．全部 2．干线 3．周边 4．省内 5．临时
    private int lineID;//线路
    private String licensePlateint;//车牌号
    private String carrier;//承运商
    private String models;//车型
    private String driver;//司机
    private String telephone;//电话
    private int ton;//吨控
    private String remarks;//备注
    private int operatorID;//操作人员
    private int operationUnitID;//操作单位
    private Date operationTime;//操作时间
}
