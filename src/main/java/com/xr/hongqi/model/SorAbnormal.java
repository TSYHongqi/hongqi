/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SorAbnormal
 * Author:   TSYH
 * Date:     2020-01-04 15:46
 * Description: 单货异常监控表
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
 * 〈单货异常监控表〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SorAbnormal implements Serializable {
    private static final long serialVersionUID = -4459398533259217460L;
    private Integer id; //异常编号
    private Date launchDate; //发起时间
    private String launchPerson; //发起人
    private String launchCompany; //发起单位
    private int abnormalType; //异常类型	1.有单无货 2.有货无单
    private String transferint;//交接单号
    private String cargoint;//单号
    private String packageint; //合包号
    private int hedgeAbnint; //对冲异常编号
    private int aboState; //状态	1.已处理2.未处理3.全部
    private Date handleDate; //处理时间

}