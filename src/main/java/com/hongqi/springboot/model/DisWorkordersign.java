package com.hongqi.springboot.model;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * dis_workordersign
 */
@Data
public class DisWorkordersign implements Serializable {

 private static final long serialVersionUID = -3322399989335331610L;
    private Integer id;//编号
    private Integer  workOrderID;//工作单ID
    private String workSheetNo;//工作单号
    private Integer   workOrderType;//工作单类型	1=返单2=调单 3=正常单据
    private Integer signType;//签收类型	1.正常签收,2.反向签收
    private String  courierint;//派送员工号
    private String courierName;//派送员工姓名
    private String  recipient;//签收人
    private Integer signUnit;//签收单位
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date  signTime;//签收时间
    private Integer invalidateMark;//作废标记	1=是 2=否
    private String abnormalMark;//暂无
    private String inputPersonCode;//录入人编码
    private Integer inputPersonID;//录入人	外键，对应到用户表编号
    private Integer inputID;//录入单位
   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
   @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date inputTime;//录入时间
    private String recipientName;//签收人姓名
    private Integer status;

   /**
    * 新增
    */
   private String empName;//录入人
   private String name;//录入单位
   private String uname;//签收单位

}
