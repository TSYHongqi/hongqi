package com.hongqi.springboot.model;


import lombok.Data;

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
    private Integer  courierint;//派送员工号
    private String courierName;//派送员工姓名
    private String  recipient;//签收人
    private Integer signUnit;//签收单位
    private Date  signTime;//签收时间
    private Integer invalidateMark;//作废标记	1=是 2=否
    private String abnormalMark;//暂无
    private Integer inputPersonCode;//录入人编码
    private Integer inputPersonID;//录入人	外键，对应到用户表编号
    private Integer inputID;//录入单位
    private Date inputTime;//录入时间

}
