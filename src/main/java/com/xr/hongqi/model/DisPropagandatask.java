package com.xr.hongqi.model;

import lombok.Data;

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
    private Date releaseTime;//发布时间
    private Date failureTime;//失效时间
    private Integer status;//状态	1.开启，2.关闭
    private String content;//宣传内容

}
