/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CheckTableDao
 * Author:   TSYH
 * Date:     2020-02-04 9:30
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.dao.dispatch;

import com.hongqi.springboot.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.AccessibleObject;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-04
 * @since 1.0.0
 */
@Repository
public interface CheckTableDao {

    /**
     * 查询定区编码（在分区中查询）
     * @return
     */
    BasZoneInfo queryZone(@Param("zoneCode") String zoneCode);

    /**
     *查询小件员工号
     */
    SyEmp queryEmp(@Param("empNo") String empNo);

    /**
     * 查询单位
     */
    SyUnits queryUnits(@Param("name") String name);


    /**
     *新增
     */
   void addBasAssociated(BasAssociatedAddress basAssociatedAddress);

   void addBasAssociMember(BasAssociateMember basAssociateMember);

    /**
     *根据id查询工单信息
     */
    AllAccBusAdmiWorkOr queryAworkById(@Param("id") Integer id);
    /**
     * 销毁工单
     */
    void destoryAccwork(@Param("id") Integer id);

     //增加新工单
    void addWorkOrder(AllAccBusAdmiWorkOr accWorkOrder);


    //修改受理表
    void updAccBusById(@Param("linkman") String linkman,@Param("phone")String phone,@Param("pickerInfo")Integer pickerInfo,@Param("processingUnit")Integer processingUnit,
                       @Param("city") String city,@Param("aid")Integer aid);

    /**
     * 查询该地址是否已经存在
     * @param customAddress
     * @return
     */
   BasAssociatedAddress findBasAssoAddr(@Param("customAddress") String customAddress);

   SyEmp queryUnitByName(@Param("name") String name);



    /**
     *增加调度信息
     */
    void addDispatchHistory(DisDispatchhistory disDispatchhistory);

    /**
     *查询调度历史表
     */
    String findDispatchHistory();


}