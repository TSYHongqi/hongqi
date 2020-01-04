package com.xr.hongqi.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * IAE_TimeInput   到达时间录入表
 */
@Data
public class IaeTimeInput implements Serializable {

    private static final long serialVersionUID = -1436706423187304044L;
    private Integer id;//出库交接单号
    private String inputType;//状态
    private String port;//到达口岸
    private Integer demand;//配载方式
    private String vehicleint;//航班车次
    private String waybillID;//货票号
    private Date expectArrivalDate;//预计到港时间
    private Date expectDepartureDate;//预计离港时间
    private Date actualArrivalDate;//实际到港时间
    private Date actualDepartureDate;//实际到港时间
    private String start;//始发站
    private Integer carriers;//承运商
    private String remarks;//备注
    private Integer  inputPerson;//录入人	外键，对应员工表员工ID
    private Date inputDate;//录入时间

}
