package com.xr.hongqi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasZoneInfo implements Serializable {
    private static final long serialVersionUID = 5546806758388107652L;
    private Integer iD;//编号
    private String zoneCode;//定区编码 唯一
    private String zoneName;//定区名称
    private int zonePeople;//定区负责人
    private String telPhone;//联系电话
    private int subordinateUnit;//所属单位	外键，对应到单位表ID
}
