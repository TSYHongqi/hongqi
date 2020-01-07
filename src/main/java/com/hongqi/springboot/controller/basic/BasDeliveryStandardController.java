/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BasicController
 * Author:   TSYH
 * Date:     2020-01-06 16:16
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.controller.basic;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongqi.springboot.model.BasDeliveryStandard;
import com.hongqi.springboot.service.BasDeliveryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-06
 * @since 1.0.0
 */
@Controller
public class BasDeliveryStandardController {

    @Autowired
    private BasDeliveryStandardService basDeliveryStandardService;
    /**
     * 首页
     * @return
     */
    @RequestMapping("/getWorkbench")
    public String getMain(){
        return "/pages/workbench";
    }

    /**
     * 收派标准
     * @return
     */
    @RequestMapping("/deliveryStandard")
    public String getStandard(){
        return "/pages/basicData/deliveryStandard";
    }



    @RequestMapping("/findDeliveryStandard")
    @ResponseBody
    public String findDeliveryStandard(@RequestParam(value="page",required=false) String page, @RequestParam(value="limit",required=false) String limit){
        System.out.println(page+"================="+limit);
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<BasDeliveryStandard> standards = basDeliveryStandardService.findStandards();

        PageInfo<BasDeliveryStandard> pageInfo = new PageInfo<BasDeliveryStandard>(standards);
        String jsonString = JSON.toJSONString(standards);

        System.out.println("json================="+jsonString);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }



























}