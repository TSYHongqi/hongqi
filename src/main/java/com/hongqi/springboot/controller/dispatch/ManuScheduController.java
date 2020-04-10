/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ManuScheduController
 * Author:   TSYH
 * Date:     2020-02-10 17:03
 * Description: 人工调度
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.controller.dispatch;

import com.hongqi.springboot.service.acceptance.BusinessAcceptanceService;
import com.hongqi.springboot.service.dispatch.ManuScheduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 〈一句话功能简述〉<br> 
 * 〈人工调度〉
 *
 * @author TSYH
 * @create 2020-02-10
 * @since 1.0.0
 */
@Controller
public class ManuScheduController {

    @Autowired
    private  ManuScheduService manuScheduService;

    @Autowired
    private BusinessAcceptanceService businessAcceptanceService;

    /**
     * 跳转人工调度
     */
    @RequestMapping("/getManuSchedu")
    public String getManuSchedu(){

        return "/pages/dispatch/manualScheduling";
    }

    /**
     * 查询人工调度信息
     */
    @RequestMapping("/getManualSchedu")
    @ResponseBody
    public String getManualSchedu(@RequestParam(value="page",required=false) String page,
                                  @RequestParam(value="limit",required=false) String limit,
                                  @RequestParam(value = "businessNoticeNo",required = false) String businessNoticeNo,
                                  @RequestParam(value = "reservationTime",required = false) String reservationTime

    ){
        return  manuScheduService.queryManuSchede(page, limit, businessNoticeNo, reservationTime);
    }


    /**
     * 分配页面
     */
    @RequestMapping("/getDistribution/{id}")
    public String getDistribution(@PathVariable("id") Integer id, HttpSession session){
        session.setAttribute("id",id);

        return "/pages/dispatch/distribution";
    }

    /**
     *查询所有单位
     */
    @RequestMapping("/queryAllUnits")
    @ResponseBody
    public String queryAllUnits(){
        String s = manuScheduService.queryAllUnits();
        return s;
    }


    /**
     * 单位下的员工号
     */
    @RequestMapping("/queryEmpbyUid")
    @ResponseBody
    public String queryEmpbyUid(@RequestParam("empUnit") Integer empUnit){
        return manuScheduService.queryEmpbyUid(empUnit);
    }

    /**
     * 分配
     */
    @RequestMapping("/addManuSchedu")
    @ResponseBody
    public String addManuSchedu(@RequestParam(value="name",required=false) String name,
                                @RequestParam(value="empNo",required=false) String empNo,
                                @RequestParam(value="id",required=false) Integer id,HttpSession session){
        return  manuScheduService.distribution(name,empNo,id,session);
    }

    /**
     * 跳转人工调度详情
     */
    @RequestMapping("/getManualSchedulingDetail/{businessNoticeNo}")
    public String getManualSchedulingDetail(HttpSession session,@PathVariable("businessNoticeNo") String businessNoticeNo){

        session.setAttribute("businessNoticeNo",businessNoticeNo);
        manuScheduService.findOneAccBusiness(session,businessNoticeNo);
        return "/pages/dispatch/manualScheduling_detail";
    }

    /**
     * 人工调度详情
     */
    @RequestMapping("/findAllDetailByBus")
    @ResponseBody
    public String findAllDetailByBus(@RequestParam("businessNoticeNo") String businessNoticeNo){
        return manuScheduService.findAllDetailByBus(businessNoticeNo);
    }

    /**
     * 跳转销毁
     */
    @RequestMapping("/ifDestoryById/{businessNoticeNo}")
    public String ifDestoryById(@PathVariable("businessNoticeNo") String businessNoticeNo){
        businessAcceptanceService.destorywork2(businessNoticeNo);
        businessAcceptanceService.destorywork(businessNoticeNo);

        return "/pages/dispatch/manualScheduling";
    }





























}