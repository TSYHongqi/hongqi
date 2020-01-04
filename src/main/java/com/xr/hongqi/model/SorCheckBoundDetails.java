/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SorCheckBoundDetails
 * Author:   TSYH
 * Date:     2020-01-04 16:01
 * Description: 盘库详情表
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.xr.hongqi.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈盘库详情表〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SorCheckBoundDetails implements Serializable {
    private static final long serialVersionUID = 8100623500189719196L;
    private Integer id;//工作单号
    private  int  cargoCount;//件数
    private double weight; //重量
    private double volume; //体积s
    private int cargoType; //类型
    private String direction;//方向
    private int StoragePerson;//入库人	外键，对应员工表员工ID
    private Date StorageDate; //入库时间



}