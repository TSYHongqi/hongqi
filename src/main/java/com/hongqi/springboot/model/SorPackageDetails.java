/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SorPackageDetails
 * Author:   TSYH
 * Date:     2020-01-04 16:38
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈合包明细表〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SorPackageDetails implements Serializable {
    private static final long serialVersionUID = -3284277780309218283L;
    private Integer id;//单号
    private int wareName; //商品名  外键，也可以改类型
    private String destination;//到达地
    private int ticket;//票数
    private int actualCargoint;//实际件数
    private int cargoint;//件数
    private double weight;//重量
    private double volume;//体积
    private Date service;//到达时限
    private  String  importantHints; //重要提示
    private String ask;//配置要求
    private  String  inputType;//输入类型

}