/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SorCheckBound
 * Author:   TSYH
 * Date:     2020-01-04 15:56
 * Description: 盘库表
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
 * 〈盘库表〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SorCheckBound implements Serializable {
    private static final long serialVersionUID = 7344573272184717757L;
    private Integer id;//盘库单号
    private String cargoSum; //扫描设备号
    private int checkPerson;//盘库人	外键，对应员工表员工ID
    private Date checkDate; //盘库时间
    private String checkCompany; //操作单位

}