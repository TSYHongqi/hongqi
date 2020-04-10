/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BusinessAcceptanceService
 * Author:   TSYH
 * Date:     2020-01-09 16:52
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.acceptance;

import com.hongqi.springboot.model.*;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-09
 * @since 1.0.0
 */
public interface BusinessAcceptanceService {

    /**
     * 查询
     * @return
     */
    List<AccBusinessAdmissibility> findBusinessAcceptance(String businessNoticeNo, String customCode);

    /**
     * 查询客户信息
     * @param mobileNo
     * @return
     */
     BasZonecustomInfo queryCusByPhone(String mobileNo);

     /**
     *该区是否存在
     */
     BasArea ifExitsAddr(String county);
    /**
     * 通过城市查询区
     */
    BasArea ifMatch(String city,String county);


    /**
     * 得到最新的业务通知单编号
     */
    String getNoticeNo();

    /**
     *通过地址匹配到取派人
     */
    BasZoneInfo queryByAddr(String pickupAddress,String county);
    /**
     * 增加受理
     */
    void addBusAcc(String businessNoticeNo,String reservationTime,
                   String customName,String pickupAddress,String customCode,
                   String linkman,String telPhone,double weight
            ,double volume,String importantHints,String arriveCity,
                 int pickerInfo,String sendAddress,int processingUnit,
                   int notificationSource,int customNoModifyFlag,
                   String singleType,int packagesNum,String production,
                   String city,String sendMan, String sendPhone,String phone);

    /**
     * 增加一条客户信息
     */
    void addCus(String customCode,String customName,
                String mobileNo,String cityName,
                String cstomAddress,int zoneInfoID);

    /**
     *查找最新的工单
     */
    AccWorkOrder queryMax();

    /**
     * 添加新工单
     */
    void addWorkOrder(String businessNoticeNo,String jobNo,int jobType,
                      int pickupStatus,int shortMessageint,int smallMemberNum,int pickupUnit,
                      String pickupTime);

    /**
     * 修改业务受理,根据id查询
     */
    AccBusinessAdmissibility queryById(Integer id);
    /**
     *修改业务受理
     */
    void updateWorking(String businessNoticeNo,String reservationTime,
                       String customName,String pickupAddress,String customCode,
                      String telPhone,double weight, String postalCode,Integer processingUnit,
                      double volume,String importantHints,String arriveCity,String sendAddress,
                       int notificationSource,int customNoModifyFlag,Integer zoneInfoID,String linkman,
                      String singleType,int packagesNum,String production,String city,Integer id,String sendMan, String sendPhone,Integer pickerInfo);
    /**
     * 追单
     */
    void updateWork(Integer jobType,Integer pickupStatus,Integer id);

    /**
     *短信序号
     */
    Integer queryShortMessageint(Integer id);

    /**
     * 根据区得到邮政编码
     */
    BasArea queryPostByCounty(String county);

    /**
     * 销毁工单
     */
    void destorywork(String businessNoticeNo);


    void destorywork2(String businessNoticeNo); //人工分单（销毁时用）
















}