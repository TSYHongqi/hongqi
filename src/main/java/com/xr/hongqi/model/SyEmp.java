/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SyEmp
 * Author:   TSYH
 * Date:     2020-01-04 14:54
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.xr.hongqi.model;

import lombok.Data;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户、员工表〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SyEmp implements Serializable {
    private static final long serialVersionUID = -1123569614278183474L;
    private Integer ID; //编号
    private String empName; //员工姓名
    private String empNo; //工号
    private String pwd;  //密码
    private String salt;  //盐
    private String queryPwd; //查台密码
    private int roleID; //角色ID
    private int empUnit; //员工所属单位
    private String remark; //备注
    private int  disabled; //禁用 1可使用 0已禁用

}