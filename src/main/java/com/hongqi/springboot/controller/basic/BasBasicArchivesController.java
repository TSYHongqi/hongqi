/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BasBasicArchivesController
 * Author:   TSYH
 * Date:     2020-01-08 15:38
 * Description: 基础档案
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.controller.basic;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongqi.springboot.model.BasBasiCarchiveSentry;
import com.hongqi.springboot.model.BasBasiCarchives;
import com.hongqi.springboot.service.basic.BasBasicArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈基础档案〉
 *
 * @author TSYH
 * @create 2020-01-08
 * @since 1.0.0
 */
@Controller
public class BasBasicArchivesController {

    @Autowired
    private BasBasicArchivesService basBasicArchivesService;

    /**
     * 基础档案
     * @return
     */
    @RequestMapping("/basicArchives")
    public String getStandard(){
        return "/pages/basicData/basicArchives";
    }

   @RequestMapping("/findAll")
   @ResponseBody
   public String findAll(@RequestParam(value="page",required=false) String page, @RequestParam(value="limit",required=false) String limit,
                         @RequestParam(value = "id",required = false) String id, @RequestParam(value = "name",required = false) String name,
                         @RequestParam(value = "empName",required = false) String empName,@RequestParam(value = "operationTime",required = false) String operationTime){
       PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
       int i = 0;
       if(id!=""&&id!=null){
           i = Integer.parseInt(id);
       }
       List<BasBasiCarchives> carchives = basBasicArchivesService.findAll(i,empName,name,operationTime);
       PageInfo<BasBasiCarchives> pageInfo = new PageInfo<BasBasiCarchives>(carchives);
       String jsonString = JSON.toJSONString(carchives);
       System.out.println(jsonString);
       String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
       return jso;
   }

    /**
     * 跳转查看列表
     */
    @RequestMapping("/basicArchivesList/{id}")
    public String checkAllList(@PathVariable("id") String id, HttpSession session){
        session.setAttribute("bAid",id);
        return "/pages/basicData/basicArchives_list";
    }

    /**
     * 查看列表（详细）
     */
    @RequestMapping("/findAllList")
    @ResponseBody
    public String findAllList(HttpSession session,@RequestParam(value="page",required=false) String page, @RequestParam(value="limit",required=false) String limit){
        Object bAid = session.getAttribute("bAid");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<BasBasiCarchiveSentry> allList = basBasicArchivesService.findAllList(Integer.parseInt(String.valueOf(bAid)));
        PageInfo<BasBasiCarchiveSentry> pageInfo = new PageInfo<BasBasiCarchiveSentry>(allList);
        String jsonString = JSON.toJSONString(allList);
        System.out.println(jsonString);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }










}