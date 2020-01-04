/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SyMemus
 * Author:   TSYH
 * Date:     2020-01-04 15:02
 * Description: 菜单表
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
 * 〈菜单表〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Component
@Data
public class SyMemus implements Serializable {
    private static final long serialVersionUID = -2628173157357081707L;
    private Integer id; //编号
    private String  parentID; //上级栏目编号
    private String type; //栏目类型，一级目录
    private String text; //栏目名称
    private String url; //栏目地址
    private String tip; //栏目提示语
    private String icon; //图标
    private boolean  isCurrent; //是否打开页面、

}