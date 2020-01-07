package com.hongqi.springboot.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BasBasiCarchives implements Serializable {
    private static final long serialVersionUID = 8716560215019234824L;
    private Integer iD;//编号
    private String name;//基本档案名称
    private int grade;//档案是否分级 1：是，0：否
    private String remarks;//备注
    private int operatorID;//操作人员
    private int operationUnitID;//操作单位
    private Date operationTime;//操作时间
}
