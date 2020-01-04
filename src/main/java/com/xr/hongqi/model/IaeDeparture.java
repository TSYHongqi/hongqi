package com.xr.hongqi.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * IAE_Departure  出港配载表
 */
@Data
public class IaeDeparture implements Serializable {

   private static final long serialVersionUID = -3468627396969392876L;
   private String id; //序号
   private String packingID; //合包号
   private Integer transfer; //中转
   private Integer actualCount; //实际数量
   private String destination; //到达地
   private Integer  wareName; //商品名，外键
   private Integer cargoCount; //件数
   private Double  weight; //重量
   private Double volume; //体积
   private Double actualVolume; //实际体积
   private Date timeLimit; //送达时限
   private String ask; //配置要求
   private String takeCargoPerson; //收货人
   private String tackCargoAddress; //收货地址
   private Integer payment;//到付款
   private String importantHints;//重要提示
   private Date surplusTime;//剩余时间
   private String entrustCompany;//委托单位


}
