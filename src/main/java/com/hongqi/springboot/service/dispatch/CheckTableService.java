/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CheckTableService
 * Author:   TSYH
 * Date:     2020-02-04 9:28
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.dispatch;

import com.hongqi.springboot.model.*;

import javax.servlet.http.HttpSession;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-04
 * @since 1.0.0
 */
public interface CheckTableService {
    /**
     * 查询定区编码
     * @return
     */
    BasZoneInfo queryZone(String zoneCode);

    /**
     *查询小件员工号
     */
    SyEmp queryEmp(String empNo);

    /**
     * 查询单位
     */
    SyUnits queryUnits(String name);
    /**
     *该区是否存在
     */
    BasArea ifExitsAddr(String county);


    /**
     * 定区编码
     * @param id
     * @param method
     * @param target
     * @param pickupAddress
     */
    String updSinZonecode(Integer id,String method,String target,String pickupAddress);

    /**
     * 小件员工号 start
     */
    String updSinSamllPeople(Integer id,String method,String target);

    /**
     * 单位
     */
    String updSinUnits(Integer id, String method, String target,HttpSession session);

    /**
     * 重发
     */
    public String reSend(Integer id);

}