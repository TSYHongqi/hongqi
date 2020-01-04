package com.xr.hongqi.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * biglog_logisticscontroltable
 */
@Data
public class biglogIogisticscontroltable implements Serializable {

    private static final long serialVersionUID = -4898496304244708570L;
    private Integer id;//录入人	外键，对应员工表员工ID
    private String workSheetNo;//工作单号
    private Integer cType;//类型
    private String corporation;//代理公司
    private String waybillID;//货票号
    private String remarks;//备注
    private Integer inputPerson;//录入人	外键，对应员工表员工ID
    private Date inputDate;//录入时间
    private Integer inputCompany;//录入单位外键，对应单位表单位ID

}
