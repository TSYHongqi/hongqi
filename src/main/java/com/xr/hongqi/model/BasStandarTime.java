package com.xr.hongqi.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BasStandarTime implements Serializable {
    private static final long serialVersionUID = 6592403489776231550L;
    private Integer iD;//编号
    private String timeName;//时间名称
    private int subordinateUnit;//所属单位	外键，对应到单位表ID;
    private Date workingTime;//平时上班时间   只需要时分，不需要日期
    private Date closingTime;//平时下班时间
    private Date saturdayWorkingTime;//周六上班时间
    private Date saturdayClosingTime;//周六下班时间
    private Date sundayWorkingTime;//周日上班时间
    private Date sundayClosingTime;//周日下班时间
}
