/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: DisCancelSign
 * Author:   TSYH
 * Date:     2020-02-08 11:15
 * Description: 取消签收确认
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈取消签收确认〉
 *
 * @author TSYH
 * @create 2020-02-08
 * @since 1.0.0
 */
@Data
public class DisCancelSign implements Serializable {
    private static final long serialVersionUID = 3502484538244726210L;

   private Integer id;//id
   private String  workSheetNo;
   private String  appNo;//申请单号
   private String  appWhy;//申请原因
   private String signType;//签收类型

   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
   @JSONField(format="yyyy-MM-dd HH:mm:ss")
   private Date signTime;//签收时间
   private String signUnit;//签收单位

   private String appUnit;//申请单位
   private String appPeople;//申请人

   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
   @JSONField(format="yyyy-MM-dd HH:mm:ss")
   private Date  appTime;//申请时间

   private String conPeople;//确认人

   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
   @JSONField(format="yyyy-MM-dd HH:mm:ss")
   private Date  conTime;//确认时间
   private int conStatus;//确认状态
   private String why;//原因
   private int disabled;//是否禁止
   private int status;//审批状态




}