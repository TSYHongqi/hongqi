/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CheckTableController
 * Author:   TSYH
 * Date:     2020-02-04 9:30
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.controller.dispatch;

import com.hongqi.springboot.model.*;
import com.hongqi.springboot.service.dispatch.CheckTableService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-04
 * @since 1.0.0
 */
@Controller
public class CheckTableController {

    @Autowired
    private CheckTableService checkTableService;

    /**
     * 跳转转单页面
     */
    @RequestMapping("/singleTurn/{pickupAddress}/{id}")
    public String singleTurn(@PathVariable Integer id, HttpSession session,@PathVariable("pickupAddress") String pickupAddress){
        session.setAttribute("id",id);
        session.setAttribute("pickupAddress",pickupAddress);
        return "/pages/dispatch/singleTurn";
    }

    /**
     * 转入目标为2，3
     * @param id
     * @param method
     * @param target
     * @return
     */
    @RequestMapping("/updSingleTurn")
    @ResponseBody
    public String updSingleTurn(@RequestParam("id") Integer id,@RequestParam("method") String method,@RequestParam("target") String target,HttpSession session){
        if(method.equals("2")){//小件员工号
           return checkTableService.updSinSamllPeople(id, method, target);
        }else {//单位
            return checkTableService.updSinUnits(id,method,target,session);
        }
    }

    /**
     * 转入目标为定区
     * @param id
     * @param method
     * @param target
     * @return
     */
    @RequestMapping("/updSinZonecode")
    @ResponseBody
    public String updSinZonecode(@RequestParam("id") Integer id,@RequestParam("method") String method,@RequestParam("target") String target,@RequestParam("pickupAddress") String pickupAddress){
        return checkTableService.updSinZonecode(id,method,target,pickupAddress);
    }


    /**
     * 验证
     * @param method
     * @param target
     * @return
     */

    @RequestMapping("/queryCheckDan")
    @ResponseBody
    public String queryCheckDan(@RequestParam("method") String method,@RequestParam("target") String target){
        if(method.equals("1")){ //定区编码
            BasZoneInfo basPartition = checkTableService.queryZone(target);
            if(basPartition==null){
                return "b";
            }else{
                return "a";
            }
        }else if(method.equals("2")){//小件员工号
            SyEmp syEmp = checkTableService.queryEmp(target);
            if(syEmp==null){
                return "b";
            }else{
                return "a";
            }
        }else {//单位
            SyUnits syUnits = checkTableService.queryUnits(target);
            if(syUnits==null){
                return "b";
            }else{
                return "a";
            }
        }
    }


    /**
     * 重发
     */
    @RequestMapping("/reSend/{id}")
    @ResponseBody
    public String reSend(@PathVariable("id") Integer id){
        String s = checkTableService.reSend(id);
        return s;
    }























}