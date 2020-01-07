/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SorStorage
 * Author:   TSYH
 * Date:     2020-01-04 16:51
 * Description: ：入库表
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
 * 〈：入库表〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SorStorage  implements Serializable {
    private static final long serialVersionUID = -3125391420899434540L;
    private Integer id;//入库交接单号
    private Date acceptDate;//接货时间
    private int acceptPerson;//接货人	外键，对应员工表员工ID
    private String acceptCompany;//接收单位
    private int deliveryPerson; //发货人	外键，对应员工表员工ID
    private String deliveryCompany; //发货单位


}