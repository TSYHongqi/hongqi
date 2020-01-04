package com.xr.hongqi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasSubstitute implements Serializable {
    private static final long serialVersionUID = -7817712199393346012L;
    private Integer iD;//编号
    private String empNo;//员工工号   对应员工表员工工号
    private String empName;//员工名称        对应员工表员姓名
    private int mobileNo;//手机号码
    private String type;//取派员类型     对应“基础档案表BAS_BasicArchives”表ID
    private int  pDA;//是否使用PDA	1：使用，0：不使用
    private double standardKg;//所属单位   外键，对应到单位表编号
    private double standardLength;//收派标准（长度）
    private String models;//车型
    private String plateint;//车牌号
    private int invalidateMark;//作废标记  1：未作废，0：已作废
    private int subordinateUnit;//所属单位   外键，对应到单位表编号
}
