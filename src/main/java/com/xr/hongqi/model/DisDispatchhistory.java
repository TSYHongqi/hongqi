package com.xr.hongqi.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * dis_dispatchhistory
 */
@Data
public class DisDispatchhistory implements Serializable {

    private static final long serialVersionUID = 8255499766552043946L;
    private Integer id;//序号
    private Integer dispatchSequence;//调度序号
    private Integer transferredUnit;//调入单位
    private Integer operatorID;// 暂无
    private Integer operationUnitID;//操作单位
    private Date operationTime;//操作时间
    private String remark;//备注
    private Integer status;//状态
    private String reason;//退回原因

}
