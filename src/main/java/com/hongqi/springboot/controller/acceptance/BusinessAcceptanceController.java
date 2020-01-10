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
import com.hongqi.springboot.model.AccBusinessAdmissibility;
import com.hongqi.springboot.model.BasBasiCarchiveSentry;
import com.hongqi.springboot.service.acceptance.BusinessAcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        for (AccBusinessAdmissibility bus : businessAcceptance) {
            String pickupAddress = bus.getPickupAddress();
            String substring = pickupAddress.substring(pickupAddress.indexOf("省") + 1, pickupAddress.indexOf("市"));
            bus.setPickupCity(substring);
        }
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
    public String findAll(){
        return "/pages/acceptance/businessAcceptance_add";
    }
























}