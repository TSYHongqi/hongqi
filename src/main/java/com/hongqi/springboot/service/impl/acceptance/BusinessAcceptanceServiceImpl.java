/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BusinessAcceptanceServiceImpl
 * Author:   TSYH
 * Date:     2020-01-09 16:55
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.impl.acceptance;

import com.hongqi.springboot.dao.acceptance.BusinessAcceptanceDao;
import com.hongqi.springboot.model.*;
import com.hongqi.springboot.service.acceptance.BusinessAcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author TSYH
 * @create 2020-01-09
 * @since 1.0.0
 */
@Service
public class BusinessAcceptanceServiceImpl implements BusinessAcceptanceService {

    @Autowired
    private BusinessAcceptanceDao businessAcceptanceDao;

    @Override
    public List<AccBusinessAdmissibility> findBusinessAcceptance(String businessNoticeNo, String customCode) {
        return businessAcceptanceDao.findBusinessAcceptance(businessNoticeNo,customCode);
    }

    @Override
    public BasZonecustomInfo queryCusByPhone(String mobileNo) {
        return businessAcceptanceDao.queryCusByPhone(mobileNo);
    }


    @Override
    public BasArea ifExitsAddr(String county) {
        return businessAcceptanceDao.ifExitsAddr(county);
    }

    @Override
    public BasArea ifMatch(String city,String county) {
        return businessAcceptanceDao.ifMatch(city,county);
    }

    /**
     * 得到最新的业务通知单编号
     * @return
     */
    @Override
    public String getNoticeNo() {
        AccBusinessAdmissibility noticeNo = businessAcceptanceDao.getNoticeNo();
        String newNoticeNo ="";
        if(noticeNo!=null) {
            String businessNoticeNo = noticeNo.getBusinessNoticeNo();
            String substring = businessNoticeNo.substring(3);
            int i = Integer.parseInt(substring);
            int a = i + 1;//将该业务通知单编号+1
            newNoticeNo = "YWD" + a;
        }else{
            newNoticeNo = "YWD10001";
        }
        return newNoticeNo;
    }

    /**
     * 得到用户的地址
     * @param pickupAddress
     * @param county
     * @return
     */
    @Override
    public BasZoneInfo queryByAddr(String pickupAddress, String county) {
        return businessAcceptanceDao.queryByAddr(pickupAddress,county);
    }

    @Override
    public void addBusAcc(String businessNoticeNo, String reservationTime, String customName, String pickupAddress, String customCode, String linkman, String telPhone,
                          double weight, double volume, String importantHints, String arriveCity, int pickerInfo, String sendAddress, int processingUnit, int notificationSource, int customNoModifyFlag, String singleType,
                          int packagesNum, String production, String city,String sendMan, String sendPhone,String phone) {
        businessAcceptanceDao.addBusAcc(businessNoticeNo,reservationTime,customName,pickupAddress,customCode,linkman,telPhone,weight
                ,volume,importantHints,arriveCity,pickerInfo,sendAddress,processingUnit,notificationSource,customNoModifyFlag,
                singleType,packagesNum,production,city,sendMan,sendPhone,phone);
    }


    @Override
    public void addCus(String customCode, String customName, String mobileNo, String cityName, String cstomAddress, int zoneInfoID) {
        businessAcceptanceDao.addCus(customCode,customName,mobileNo,cityName,cstomAddress,zoneInfoID);
    }

    @Override
    public AccWorkOrder queryMax() {
        return businessAcceptanceDao.queryMax();
    }

    @Override
    public void addWorkOrder(String businessNoticeNo, String jobNo, int jobType, int pickupStatus, int shortMessageint, int smallMemberNum, int pickupUnit,String pickupTime) {
        businessAcceptanceDao.addWorkOrder(businessNoticeNo,jobNo,jobType,pickupStatus,shortMessageint,smallMemberNum,pickupUnit,pickupTime);
    }

    @Override
    public AccBusinessAdmissibility queryById(Integer id) {
       return businessAcceptanceDao.queryById(id);
    }

    @Override
    public void updateWorking(String businessNoticeNo, String reservationTime, String customName, String pickupAddress,
                              String customCode, String telPhone, double weight, String postalCode, Integer processingUnit,
                              double volume, String importantHints, String arriveCity, String sendAddress, int notificationSource,
                              int customNoModifyFlag, Integer zoneInfoID, String linkman, String singleType, int packagesNum,
                              String production, String city, Integer id,String sendMan, String sendPhone,Integer pickerInfo) {
        businessAcceptanceDao.updateWorking(businessNoticeNo,reservationTime,customName, pickupAddress,
                customCode,telPhone,weight,postalCode,processingUnit,volume,
                importantHints,arriveCity,sendAddress,notificationSource,customNoModifyFlag, zoneInfoID,
                linkman,singleType,packagesNum,production,city,id,sendMan,sendPhone,pickerInfo);
    }

    @Override
    public void updateWork(Integer jobType, Integer pickupStatus, Integer id) {
        businessAcceptanceDao.updateWork(jobType,pickupStatus,id);
    }

    @Override
    public Integer queryShortMessageint(Integer id) {
        return businessAcceptanceDao.queryShortMessageint(id);
    }


    @Override
    public BasArea queryPostByCounty(String county) {
        return businessAcceptanceDao.queryPostByCounty(county);
    }

    @Override
    public void destorywork(String businessNoticeNo) {
        businessAcceptanceDao.destorywork(businessNoticeNo);
    }

    @Override
    public void destorywork2(String businessNoticeNo) {
        businessAcceptanceDao.destorywork2(businessNoticeNo);
    }


    public void get(String mobileNo){
        BasZonecustomInfo basZonecustomInfo = queryCusByPhone(mobileNo);
    }
}