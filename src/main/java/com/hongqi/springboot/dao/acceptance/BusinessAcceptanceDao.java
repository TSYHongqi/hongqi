/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BusinessAcceptanceDao
 * Author:   TSYH
 * Date:     2020-01-09 16:35
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.dao.acceptance;

import com.hongqi.springboot.controller.acceptance.BusinessAcceptanceController;
import com.hongqi.springboot.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-09
 * @since 1.0.0
 */
@Repository
public interface BusinessAcceptanceDao {

   /**
    * 查询
    * @return
    */
   List<AccBusinessAdmissibility> findBusinessAcceptance(@Param("businessNoticeNo") String businessNoticeNo,
                                                         @Param("customCode") String customCode);

   /**
    * 查询客户信息
    * @param mobileNo
    * @return
    */
   BasZonecustomInfo queryCusByPhone(@Param("mobileNo") String mobileNo);

   /**
    * 该区是否存在
    */
   BasArea ifExitsAddr(@Param("county") String county);

   /**
    * 通过城市查询区
    */
   BasArea ifMatch(@Param("city") String city,@Param("county") String county);
   /**
    * 生成业务通知单
    */
   AccBusinessAdmissibility getNoticeNo();

   /**
    *通过地址匹配到取派人
    */
   BasZoneInfo queryByAddr(@Param("pickupAddress") String pickupAddress,@Param("county") String county);

   /**
    * 增加受理
    */
   void addBusAcc(@Param("businessNoticeNo") String businessNoticeNo,@Param("reservationTime")String reservationTime,
                  @Param("customName") String customName,@Param("pickupAddress")String pickupAddress,@Param("customCode")String customCode,
                  @Param("linkman")String linkman,@Param("telPhone") String telPhone,@Param("weight") double weight
           ,@Param("volume") double volume,@Param("importantHints") String importantHints,@Param("arriveCity") String arriveCity,
                  @Param("pickerInfo") int pickerInfo,@Param("sendAddress") String sendAddress,@Param("processingUnit") int processingUnit,
                  @Param("notificationSource") int notificationSource,@Param("customNoModifyFlag") int customNoModifyFlag,
                  @Param("singleType") String singleType,@Param("packagesNum") int packagesNum,@Param("production") String production,@Param("city") String city,@Param("sendMan")
                          String sendMan,@Param("sendPhone") String sendPhone,@Param("phone") String phone);

/**
 * 增加一条客户信息
 */
void addCus(@Param("customCode") String customCode,@Param("customName") String customName,
            @Param("mobileNo") String mobileNo,@Param("cityName") String cityName,
            @Param("cstomAddress") String cstomAddress,@Param("zoneInfoID") int zoneInfoID);


/**
 *查找最新的工单
 */
AccWorkOrder queryMax();

/**
 * 添加新工单
 */
void addWorkOrder(@Param("businessNoticeNo") String businessNoticeNo,@Param("jobNo") String jobNo,@Param("jobType") int jobType,
          @Param("pickupStatus") int pickupStatus,@Param("shortMessageint") int shortMessageint,@Param("smallMemberNum") int smallMemberNum,
                  @Param("pickupUnit") int pickupUnit,@Param("pickupTime") String pickupTime);
    /**
     * 添加新工单
     */
    void addWorkOrder1(@Param("businessNoticeNo") String businessNoticeNo,@Param("jobNo") String jobNo,@Param("jobType") int jobType,
                      @Param("pickupStatus") int pickupStatus,@Param("shortMessageint") int shortMessageint,@Param("smallMemberNum") int smallMemberNum,
                      @Param("pickupUnit") int pickupUnit,@Param("pickupTime") Date pickupTime);



/**
 * 修改业务受理,根据id查询
 */
AccBusinessAdmissibility queryById(@Param("id") Integer id);

/**
 *修改业务受理
 */
  void updateWorking(@Param("businessNoticeNo") String businessNoticeNo,@Param("reservationTime")String reservationTime,
                     @Param("customName") String customName,@Param("pickupAddress")String pickupAddress,@Param("customCode")String customCode,
                     @Param("telPhone") String telPhone,@Param("weight") double weight,@Param("postalCode") String postalCode,@Param("processingUnit") Integer processingUnit,
          @Param("volume") double volume,@Param("importantHints") String importantHints,@Param("arriveCity") String arriveCity,@Param("sendAddress") String sendAddress,
                     @Param("notificationSource") int notificationSource,@Param("customNoModifyFlag") int customNoModifyFlag,@Param("zoneInfoID")Integer zoneInfoID,@Param("linkman") String linkman,
                     @Param("singleType") String singleType,@Param("packagesNum") int packagesNum,@Param("production") String production,@Param("city") String city,@Param("id") Integer id,@Param("sendMan")
                     String sendMan,@Param("sendPhone") String sendPhone,@Param("pickerInfo") Integer pickerInfo);
/**
 * 追单
 */
  void updateWork(@Param("jobType") Integer jobType,@Param("pickupStatus") Integer pickupStatus,@Param("id") Integer id);

   /**
    *
    */
   Integer queryShortMessageint(@Param("id") Integer id);


    /**
     * 根据区得到邮政编码
     */
   BasArea queryPostByCounty(@Param("county") String county);


    /**
     * 销毁工单
     */
    void destorywork(@Param("businessNoticeNo") String businessNoticeNo);

    void destorywork2(@Param("businessNoticeNo") String businessNoticeNo);

}