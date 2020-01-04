/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SyRoleMenus
 * Author:   TSYH
 * Date:     2020-01-04 15:40
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
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SyRoleMenus implements Serializable {
    private static final long serialVersionUID = -825470660333956003L;
    private Integer id; //编号
    private Integer roleID; //对应角色表ID
    private Integer menuID; //对应栏目表ID

}