package com.hongqi.springboot.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasAssociatedAddress implements Serializable {
    private static final long serialVersionUID = 1505375357690656773L;
    private Integer iD;//编号
    private String zoneCode;//定区编码	外键，对应到定区信息表编码
    private String city;//城市
    private String customAddress;//客户地址
    private int associatedPartitions;//已关联分区 外键，对应到分区表I
}
