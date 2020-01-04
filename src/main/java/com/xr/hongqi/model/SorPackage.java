/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SorPackage
 * Author:   TSYH
 * Date:     2020-01-04 16:27
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.xr.hongqi.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Destination;
import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈合包表〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SorPackage implements Serializable {
    private static final long serialVersionUID = -4663997779472455081L;
    private Integer id;
    private int packagePerson; //合包人	外键，对应员工表员工ID
    private String destination;//到达地
    private String reckonDes;//计算到达地
    private Date timeLimit; //送达时限
    private int TicketSum;//总票数
    private int cargoSum;//总件数
    private double weightSum;//总重量
    private double volumeSum;//总体积
    private int  state;//状态
    private int ask;//配载要求,根据需求再建立一张表，
    // 无、禁航空铁路、禁发航空

}