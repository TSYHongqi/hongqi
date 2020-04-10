package com.hongqi.springboot.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
public class AccBusinessAdmissibility implements Serializable {
    private static final long serialVersionUID = -6649628507626348761L;
    private Integer id;//编号
    private String businessNoticeNo;//业务通知单号
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
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

    private Integer awid;//编号
    private String jobNo;//工单号
    private int jobType;//工单类型	1:新,2:追,3:销
    private int pickupStatus;//取件状态	1:新单,2:已通知,3:已确认4:已取件,5:已取消
    private int  shortMessageint;//短信序号;
    private Date workGenerationTime;//工单生成时间
    private int afterSingleNum;//追单次数	新单默认为0
    private int smallMemberNum;//小件员编号
    private int pickupUnit;//取件单位	外键，对应到单位表ID
    private Date pickupTime;//取件时间
    private String sortingCode;//分拣编码

    /**
     * 新加
     */
    private String production; //产品,数据库新加字段
    private String city; //取件城市
    private String sendMan;//收件人
    private String sendPhone;//收件电话
    private String acity;//取件城市1
    private String phone;//联系人号码
    private String name;//处理单位


}
