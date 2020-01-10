package com.hongqi.springboot.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class AccBusinessAdmissibility implements Serializable {
    private static final long serialVersionUID = -6649628507626348761L;
    private Integer id;//编号
    private String businessNoticeNo;//业务通知单号
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date reservationTime;//预约收件时间
    private String customName;//客户名称
    private String pickupAddress;//取件地址
    private String customCode;//客户编号
    private String linkman;//联系人
    private String telPhone;//电话
    private double weight;//重量
    private String volume;//体积
    private String importantHints;//重要提示
    private String arriveCity;//到达城市
    private int pickerInfo;//取货人员信息
    private String sendAddress;//派送地址
    private int processingUnit;//处理单位	外键，对应到单位表ID
    private int notificationSource;//通知单来源
    private int customNoModifyFlag;//客户单号修改标志
    private String singleType;//分单类型
    private int packagesNum;//件数
    private double actualWeight;//计费重量
    private double packingFee;//包装费
    private int actualPacking;//实际包装 1.木箱、2.纸箱

    /**
     * 新加
     */
    private String production; //产品
    private String pickupCity; //取件城市，从地址中解析出来



}
