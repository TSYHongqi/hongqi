package com.hongqi.springboot.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BasBasicArchiveSentry implements Serializable {
    private static final long serialVersionUID = -7993963180699106462L;
    private Integer id;//编号
    private String name;//档案名称、唯一
    private int parentID;//上级编码（基础档案表ID）
    private String mnemonicCode;//助记码  唯一且只可为英文
    private int avaliable;//暂无
    private String remarks;//备注
    private int operatorID;//操作人员
    private int operationUnitID;//操作单位
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date operationTime;//操作时间


    /**
     * 详细档案
     */
    private String empName; //操作人员
    private String uname;//操作单位

}
