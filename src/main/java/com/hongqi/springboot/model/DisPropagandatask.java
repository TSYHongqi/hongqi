package com.hongqi.springboot.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Date;

/**
 * dis_propagandatask
 */
@Data
public class DisPropagandatask implements Serializable {

    private static final long serialVersionUID = 2078006741847927890L;
    private Integer id;//编号
    private String outline;//宣传概要

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;//发布时间

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date failureTime;//失效时间

    private Integer status;//状态	1.开启，2.关闭
    private String content;//宣传内容
    private Integer operatorID;
    private Integer operationUnitID;

    /**
     * 新增
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;//修改时间
    private String empName;//操作人
    private String name;//单位

}
