/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SorOutBoundDetails
 * Author:   TSYH
 * Date:     2020-01-04 16:21
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.xr.hongqi.model;

import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SorOutBoundDetails implements Serializable {
    private static final long serialVersionUID = 4644732315767242364L;
    private Integer id; //单号
    private String packageID; //合包号
    private double weight; //重量
    private double volume; //体积
    private Date scanDate;//状态
    private int  isScan; //是否扫描	1：是   0：否
    private int isNextStorage; //是否下环节入库	1：是   0：否
    private int isDoubleStorage; //是否二次入库	1：是   0：否


}