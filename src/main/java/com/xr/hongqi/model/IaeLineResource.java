package com.xr.hongqi.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * IAE_LineResource  配载线路资源表
 */
@Data
public class IaeLineResource implements Serializable {

    private static final long serialVersionUID = 5950692458578384904L;
    private Integer id;//工作单号
    private String resourceType;//状态
    private String port;//到达口岸
    private Integer demand;//配载方式
    private String vehicleint;//航班车次
    private String capacity;//运力
    private String waybillID;//货票号
    private String carrier;//承运商
    private Date expectArrivalDate;//预计到港时间
    private Date expectDepartureDate;//预计离港时间
    private Integer ticket;//票数
    private Integer cargo;//件数
    private Double weight;//重量
    private Double volume;//体积
    private String handlePerson;//处理人
    private Date handleDate;//处理时间
    private String enterPerson;//确认人	外键，对应员工表员工ID
    private Date enterDate;//确认时间
    private String enterCompany;//确认单位
    private String acceptPerson;//接货人	外键，对应员工表员工ID
    private String acceptDate;//接收时间
    private Integer acceptCompany;//接收单位
    private Integer abnormalRemarks;//异常备注
    private Integer workint;//工作单号
    private Integer transfer;//中转
    private Integer actualCount;//实际数量
    private Integer destination;//到达地
    private String  wareName;//品名
    private Double actualVolume;//实际体积
    private Date timeLimit;//送达时限
    private String ask;//配置要求
    private String takeCargoPerson;//收货人
    private String tackCargoAddress;//收货地址
    private Integer payment;//到付款
    private String specialEnsure;//特殊保障
    private Integer deliveryType;//发货类型
    private String importantHints;//重要提示
    private Date surplusTime;//剩余时间

}
