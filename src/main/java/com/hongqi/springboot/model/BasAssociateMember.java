package com.hongqi.springboot.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BasAssociateMember implements Serializable {
    private static final long serialVersionUID = -232882027588478632L;
    private Integer id;//编号
    private String zoneCode;//定区编码	外键，对应到定区信息表编码
    private String empNo;//取派人工号	对应员工表员工工号
    private String empName;//取派人名称	对应员工表员姓名
    private double standardKg;//收派标准（公斤）
    private double standardLength;//收派标准（长度)
    private Date standardTime;//收派时间
    private int subordinateUnit;//所属单位	外键，对应到单位表ID
    private String type;//类型
}
