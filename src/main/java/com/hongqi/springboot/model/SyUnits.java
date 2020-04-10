/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SyUnits
 * Author:   TSYH
 * Date:     2020-01-04 15:43
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.model;

import com.alibaba.fastjson.annotation.JSONField;
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
public class SyUnits implements Serializable {
    private static final long serialVersionUID = 2829313013202019147L;
    private Integer id; //编号
    private String  name; //单位名称
    private String remarks; //备注
    private int  operatorID; //操作人员
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date operationTime; //操作时间


    private String empName;//员工姓名
    private String empNo;//员工编号

}