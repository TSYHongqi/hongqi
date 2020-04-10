/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ProTaskController
 * Author:   TSYH
 * Date:     2020-02-06 13:33
 * Description: 宣传任务
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.controller.dispatch;

import com.hongqi.springboot.model.DisPropagandatask;
import com.hongqi.springboot.service.dispatch.ProTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 〈一句话功能简述〉<br> 
 * 〈宣传任务〉
 *
 * @author TSYH
 * @create 2020-02-06
 * @since 1.0.0
 */
@Controller
public class ProTaskController {

    @Autowired
    private ProTaskService proTaskService;
    /**
     * 跳转宣传任务
     * @return
     */
    @RequestMapping("/propagandaTask")
    public String getPropagandaTask(){
        return "/pages/dispatch/propagandaTask";
    }

    /**
     * 查询宣传任务
     */
    @RequestMapping("/findPropagandaTask")
    @ResponseBody
    public String findPropagandaTask(@RequestParam(value="page",required=false) String page,
                                     @RequestParam(value="limit",required=false) String limit,
                                     @RequestParam(value = "outline",required = false) String outline,
                                     @RequestParam(value = "releaseTime",required = false) String releaseTime,
                                     @RequestParam(value = "failureTime",required = false) String failureTime,
                                     @RequestParam(value = "status",required = false) String status
                                   ){

        return proTaskService.findDisProTask(page, limit, outline, releaseTime, failureTime, status);

    }

    /**
     * 跳转新增页面
     */
    @RequestMapping("/propagandaTaskAdd")
    public String propagandaTaskAdd(){
        return "/pages/dispatch/propagandaTask_add";
    }

    /**
     * 新增
     */
    @RequestMapping("/addPropagandaTask")
    public String addPropagandaTask(DisPropagandatask disPropagandatask, HttpSession session){
        //新增
        proTaskService.addDisProTask(disPropagandatask,session);
        return "/pages/dispatch/propagandaTask";
    }

    /**
     * 跳转修改页面
     */
    @RequestMapping("/proTaskUpd/{id}")
    public String proTaskUpd(@PathVariable("id") Integer id,HttpSession session){
        session.setAttribute("id",id);
        return "/pages/dispatch/propagandaTask_upd";
    }

    /**
     * 绑定需要修改数据
     */
    @RequestMapping("/queryDisProTaskById")
    @ResponseBody
    public String queryDisProTaskById(@RequestParam("id") Integer id){
        String disProTask = proTaskService.findDisProTask(id);
        return disProTask;
    }

    /**
     * 修改宣传任务
     */
    @RequestMapping("/updPropagandaTask")
    public String updPropagandaTask(DisPropagandatask disPropagandatask){
        proTaskService.updPropagandaTask(disPropagandatask);
        //修改
        return "/pages/dispatch/propagandaTask";
    }

    /**
     * 跳转详细页面
     */
    @RequestMapping("/propagandaTaskDetail/{id}")
    public String propagandaTaskDetail(@PathVariable("id") Integer id,HttpSession session){
        session.setAttribute("id",id);
        return "/pages/dispatch/propagandaTask_detail";
    }











}