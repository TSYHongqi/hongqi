/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SorOutBound
 * Author:   TSYH
 * Date:     2020-01-04 16:11
 * Description: 出库表
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
 * 〈出库表〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SorOutBound implements Serializable {
    private static final long serialVersionUID = 1588970584781063450L;
    private Integer outBoundID; //出库交接单号
   private int handoverType; //交接单类型
   private String line;//线路资源
   private int direction;//方向
   private int acceptPerson;//接货人	外键，对应员工表员工ID
   private String carriers;
   private String deliveryPerson;//出货人
   private String deliveryCompany;//出货单位
   private int enterPerson;//确认人	外键，对应员工表员工ID
   private Date enterDate;//确认时间ss



}