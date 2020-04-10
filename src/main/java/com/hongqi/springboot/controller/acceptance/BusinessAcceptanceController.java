/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BusinessAcceptance
 * Author:   TSYH
 * Date:     2020-01-09 9:26
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.controller.acceptance;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongqi.springboot.model.*;
import com.hongqi.springboot.service.acceptance.BusinessAcceptanceService;
import com.hongqi.springboot.service.dispatch.CheckTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈业务受理〉
 *
 * @author TSYH
 * @create 2020-01-09
 * @since 1.0.0
 */
@Controller
public class BusinessAcceptanceController {

    @Autowired
    private BusinessAcceptanceService businessAcceptanceService;


    /**
     * 跳转
     */
    @RequestMapping("/businessAcceptance")
    public String getBusinessAcce(){
        return "/pages/acceptance/businessAcceptance";
    }

    @RequestMapping("/findBusinessAcceptance")
    @ResponseBody
    public String findBusinessAcce(@RequestParam(value="page",required=false) String page, @RequestParam(value="limit",required=false) String limit,
                                   @RequestParam(value="businessNoticeNo",required=false) String businessNoticeNo, @RequestParam(value="customCode",required=false) String customCode){
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<AccBusinessAdmissibility> businessAcceptance = businessAcceptanceService.findBusinessAcceptance(businessNoticeNo, customCode);
        PageInfo<AccBusinessAdmissibility> pageInfo = new PageInfo<AccBusinessAdmissibility>(businessAcceptance);
        String jsonString = JSON.toJSONString(businessAcceptance);
        System.out.println(jsonString);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }


    /**
     * 跳转新增页面
     * @return
     */
    @RequestMapping("/businessAcceptanceAdd")
    public String findAll(HttpSession session){
        String noticeNo = businessAcceptanceService.getNoticeNo();
        session.setAttribute("noticeNo",noticeNo);
        return "/pages/acceptance/businessAcceptance_add";
    }

    /**
     * 通过手机号码查询用户
     */
    @RequestMapping("/queryCusByPhone/{telPhone}")
    @ResponseBody
    public String getBusiness(@PathVariable("telPhone") String telPhone){

        //根据手机号码查询客户
        BasZonecustomInfo basZonecustomInfo = businessAcceptanceService.queryCusByPhone(telPhone);
        String s = JSON.toJSONString(basZonecustomInfo);
        return s;
    }


    /**
     *查询用户输入的区是否正确
     */
     @RequestMapping("/ifExitsAddr/{pickupAddress}")
     @ResponseBody
     public String ifExitsAddr(@PathVariable("pickupAddress") String pickupAddress){
             String county = pickupAddress.substring(0,pickupAddress.indexOf('区'));
             BasArea basArea = businessAcceptanceService.ifExitsAddr(county);
             String s = JSON.toJSONString(basArea);
             return s;

     }




    /**
     * 通过城市查询区
    */
    @RequestMapping("/ifMatch")
    @ResponseBody
    public String ifMatch(@RequestParam("city") String city,
                          @RequestParam("pickupAddress") String pickupAddress){
        String county = pickupAddress.substring(0,pickupAddress.indexOf('区'));
        String city2 = city.substring(0,city.indexOf('市'));
        BasArea basArea = businessAcceptanceService.ifMatch(city2,county);
        if(basArea!=null){
            return "1";
        }
        return "0";
    }


    /**
     * 判断城市是否存在
     * @param arriveCity
     * @return
     */
    @RequestMapping("/ifCity")
    @ResponseBody
    public String ifCity(@RequestParam("arriveCity") String arriveCity){
        String city2 = arriveCity.substring(0,arriveCity.indexOf('市'));
        BasArea basArea = businessAcceptanceService.ifMatch(city2,null);
        if(basArea != null){
            return "1";
        }
        return "0";
    }

    /**
     *添加受理信息
     */
    @RequestMapping("/addwork")
    @ResponseBody
    public String addWork(String businessNoticeNo,String customCode,
    String customName ,String telPhone,String sendAddress ,String pickupAddress,String arriveCity
                          ,String packagesNum,String reservationTime ,String production,String weight
                          ,String volume ,String importantHints,String city,String sendMan, String sendPhone){
        String county = pickupAddress.substring(0,pickupAddress.indexOf('区'));
        BasZoneInfo basZoneInfo = businessAcceptanceService.queryByAddr(pickupAddress, county);
        if(basZoneInfo != null) {
            String linkman = basZoneInfo.getEmpName(); //联系人
            String phone = basZoneInfo.getPhone();//员工手机号码
            int pickerInfo = basZoneInfo.getEid();//取货人员信息
            int processingUnit = basZoneInfo.getEmpUnit();//处理单位
            Integer id = basZoneInfo.getId();//
            String postalCode = basZoneInfo.getPostalCode();//邮政编码
            //增加一条业务受理记录
            businessAcceptanceService.addBusAcc(businessNoticeNo,reservationTime,customName,pickupAddress,customCode,linkman,telPhone,Double.parseDouble(weight)
                    ,Double.parseDouble(volume),importantHints,arriveCity.substring(0,arriveCity.indexOf('市')),pickerInfo,sendAddress,processingUnit,1,1,
                    "系统分单",Integer.parseInt(packagesNum),production,city.substring(0,city.indexOf('市')),sendMan,sendPhone,phone); //增加
            //产生新工单
            String jobNo ="";
            int shortMessageint = 0;//短信序号
            AccWorkOrder accWorkOrder = businessAcceptanceService.queryMax();
            //根据取货人员信息得到短信序号
            Integer accWorkOrder1 = businessAcceptanceService.queryShortMessageint(pickerInfo);
            if(accWorkOrder1 != null){
                shortMessageint= accWorkOrder1 + 1;
            }else{
                shortMessageint= 10001;
            }

            if(accWorkOrder!=null){
                String substring = accWorkOrder.getJobNo().substring(2);
                int i = Integer.parseInt(substring)+1;
                jobNo = "GD"+i;
            }else{
                jobNo = "GD"+ 10001;
            }

            businessAcceptanceService.addWorkOrder(businessNoticeNo,jobNo,1,1,shortMessageint,pickerInfo,processingUnit,reservationTime);

            BasZonecustomInfo basZonecustomInfo = businessAcceptanceService.queryCusByPhone(telPhone);
            if(basZonecustomInfo==null){
                //新增一条客户记录
                businessAcceptanceService.addCus(customCode,customName,telPhone,postalCode,pickupAddress,id);
            }
            return shortMessageint+"【新】"+businessNoticeNo+" "+customName+" "+pickupAddress+" "+telPhone+" "+weight+" "+volume+" "+sendAddress+" "+importantHints;
        }else{
            System.out.println("人工受理");
            //增加一条人工调度
            businessAcceptanceService.addBusAcc(businessNoticeNo,reservationTime,customName,pickupAddress,customCode,null,telPhone,Double.parseDouble(weight)
                    ,Double.parseDouble(volume),importantHints,arriveCity.substring(0,arriveCity.indexOf('市')),0,sendAddress,0,1,1,
                    "人工受理",Integer.parseInt(packagesNum),production,city.substring(0,city.indexOf('市')),sendMan,sendPhone,null);
            BasZonecustomInfo basZonecustomInfo = businessAcceptanceService.queryCusByPhone(telPhone);
            BasArea basArea = businessAcceptanceService.queryPostByCounty(county);
            int postalCode = basArea.getPostalCode();

            if(basZonecustomInfo==null){
                //新增一条客户记录
                businessAcceptanceService.addCus(customCode,customName,telPhone, String.valueOf(postalCode),pickupAddress,0);
            }
            return "已转人工受理";
        }

    }


    /**
     * 跳转修改页面
     */
    @RequestMapping("/businessAcceptanceUpdate/{bid}")
    public String businessAcceptanceUpdate(@PathVariable("bid") Integer bid,HttpSession session){
        session.setAttribute("id",bid);
        return "/pages/acceptance/businessAcceptance_update";
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public String queryById(@RequestParam("id") Integer id){
        System.out.println(id);
        AccBusinessAdmissibility accBusinessAdmissibility = businessAcceptanceService.queryById(id);
        String s = JSON.toJSONString(accBusinessAdmissibility);
        return s;
    }

    /**
     * 获取短信序号
     */
    public Integer getMessageint(Integer id){
        Integer old = businessAcceptanceService.queryShortMessageint(id);
        int messageint = 0;
        if(old != null){
           return messageint= old + 1;
        }else{
            return  messageint= 10001;
        }
    }

    /**
     * 修改受理
     */
    @RequestMapping("/updateWork")
    @ResponseBody
    public String updateWork(String businessNoticeNo,String customCode,
                             String customName ,String telPhone,String sendAddress ,String pickupAddress,String arriveCity
            ,String packagesNum,String reservationTime ,String production,String weight
            ,String volume ,String importantHints,String city,Integer id,String sendMan, String sendPhone){
        //System.out.println(city+","+telPhone);
        String county = pickupAddress.substring(0,pickupAddress.indexOf('区'));
        BasZoneInfo basZoneInfo = businessAcceptanceService.queryByAddr(pickupAddress, county);
        if(basZoneInfo!=null) {
            String linkman = basZoneInfo.getEmpName(); //联系人
            int pickerInfo = basZoneInfo.getEid();//取货人员信息
            int processingUnit = basZoneInfo.getEmpUnit();//处理单位
            Integer zoneInfoID = basZoneInfo.getId();//取件人id
            String postalCode = basZoneInfo.getPostalCode();//邮政编码

            AccBusinessAdmissibility ac = businessAcceptanceService.queryById(id); //根据id获取受理单

            businessAcceptanceService.updateWorking(businessNoticeNo,reservationTime,customName, pickupAddress,
                    customCode,telPhone,Double.parseDouble(weight),postalCode,processingUnit,Double.parseDouble(volume),
                    importantHints,arriveCity.substring(0, arriveCity.indexOf('市')),sendAddress,1,1, zoneInfoID,
                    linkman,"系统分单",Integer.parseInt(packagesNum),production,city.substring(0, city.indexOf('市')),id,sendMan,sendPhone,pickerInfo);
            System.out.println("新获取的收件员"+pickerInfo+"===========原来的收件员"+ac.getPickerInfo());

            if(pickerInfo!=ac.getPickerInfo()){
                //生成销工单
                //短信序号+【销】+业务通知单号+客户名称+联系地址
                System.out.println(getMessageint(ac.getPickerInfo())+"销"+ac.getBusinessNoticeNo()+","+ac.getPickupAddress());
                businessAcceptanceService.destorywork(businessNoticeNo);
                //生成一条新工单
                String jobNo ="";

                AccWorkOrder accWorkOrder = businessAcceptanceService.queryMax();
                //根据取货人员信息得到短信序号
                int shortMessageint = getMessageint(pickerInfo);//短信序号

                if(accWorkOrder!=null){
                    String substring = accWorkOrder.getJobNo().substring(2);
                    int i = Integer.parseInt(substring)+1;
                    jobNo = "GD"+i;
                }else{
                    jobNo = "GD"+ 10001;
                }
                businessAcceptanceService.addWorkOrder(businessNoticeNo,jobNo,1,1,shortMessageint,pickerInfo,processingUnit,reservationTime);

                BasZonecustomInfo basZonecustomInfo = businessAcceptanceService.queryCusByPhone(telPhone);
                if(basZonecustomInfo==null){
                    //新增一条客户记录
                    businessAcceptanceService.addCus(customCode,customName,telPhone,postalCode,pickupAddress,id);
                }
                return shortMessageint+"【新】"+businessNoticeNo+" "+customName+" "+pickupAddress+" "+telPhone+" "+weight+" "+volume+" "+sendAddress+" "+importantHints;
            }

            return "a";
        }else{
            return "a";
        }

    }


    /**
     * 追单
     */
    @RequestMapping("/RunWork")
    @ResponseBody
    public String runWork(@RequestParam("id") Integer id){
        businessAcceptanceService.updateWork(2,2,id);
        AccBusinessAdmissibility ac = businessAcceptanceService.queryById(id); //根据id获取受理单
        System.out.println(getMessageint(ac.getPickerInfo())+"追"+ac.getBusinessNoticeNo()+","+ac.getPickupAddress());
        System.out.println("发送短信！");
        return "a";
    }




















}