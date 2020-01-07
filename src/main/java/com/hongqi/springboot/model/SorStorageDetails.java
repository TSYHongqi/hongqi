/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SorStorageDetails
 * Author:   TSYH
 * Date:     2020-01-04 16:55
 * Description: 入库详情表
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈入库详情表〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SorStorageDetails implements Serializable {
    private static final long serialVersionUID = -1101557908103539075L;
    private Integer id;//单号
    private String packageID;//合包号
    private double weight;//重量
    private int outBoundID;//关联出库交接单	外键
    private int state;//状态

}