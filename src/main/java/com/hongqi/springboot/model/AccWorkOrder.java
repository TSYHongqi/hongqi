package com.hongqi.springboot.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class AccWorkOrder implements Serializable {
    private static final long serialVersionUID = 3380122379826983602L;
    private Integer iD;//编号
    private String businessNoticeNo;//业务通知单号   外键，对应到业务受理表“业务通知单号
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
}
