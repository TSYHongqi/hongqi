package com.hongqi.springboot.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasZonecustomInfo implements Serializable {
    private static final long serialVersionUID = -8846315577311108682L;
    private Integer customCode;//客户编号
    private String customName;//客户姓名	唯一
    private int mobileNo;//手机号码
    private String cityName;//城市
    private String cstomAddress;//客户地址
    private int zoneInfoID;//已关联定区	外键，对应到定区表ID
}
