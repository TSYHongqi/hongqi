package com.xr.hongqi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasArea implements Serializable {
    private static final long serialVersionUID = 5024194990149236855L;
    private Integer iD;//编号
    private String province;//省
    private String city;//城市
    private String county;//区(县)
    private int postalCode;//邮政编码
    private String simpleCode;//简码
    private int cityCode;//城市编码
    private int entryUnitID;//进港单位 外键，对应到单位表编号
    private int complementUnitID;//出港单位 外键，对应到单位表编号
    private int nature;//性质	1.省级 2.市辖市 3.直辖市 4.县级5.地级 6.省会
    private int theArea;//所属区域	1.东北区2.华东区 3.华南区 4.西北区 5华中区 6西南区 7华北区
}
