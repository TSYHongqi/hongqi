/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SyRole
 * Author:   TSYH
 * Date:     2020-01-04 15:34
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.xr.hongqi.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈角色名〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SyRole implements Serializable {
    private static final long serialVersionUID = -1235351164030663468L;
    private Integer id; //编号
    private String  roleName; //角色名称
    private String roleDesc; //角色描述
    private String disabled; //禁用(1使用,0禁用)


}