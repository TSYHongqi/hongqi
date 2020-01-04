package com.xr.hongqi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasPartition implements Serializable {
    private static final long serialVersionUID = 3970267163803511486L;
    private Integer iD;//编号
    private String province;//省
    private String city;//城市
    private String county;//区（县）
    private String zoneCode;//定区编码
    private String keyword;//关键字
    private int startint;//起始号
    private int terminateint;//终止号
    private int sDint;//单双号	1：单，2：双
}
