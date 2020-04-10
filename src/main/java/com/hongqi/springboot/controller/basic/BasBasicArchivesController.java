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
import com.hongqi.springboot.model.BasBasicArchiveSentry;
import com.hongqi.springboot.model.BasBasicArchives;
import com.hongqi.springboot.model.BasDeliveryStandard;
import com.hongqi.springboot.model.SyEmp;
import com.hongqi.springboot.service.basic.BasBasicArchivesService;
import com.hongqi.springboot.service.basic.BasDeliveryStandardService;
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

    @Autowired
    private BasDeliveryStandardService basDeliveryStandardService;
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
       List<BasBasicArchives> carchives = basBasicArchivesService.findAll(i,empName,name,operationTime);
       PageInfo<BasBasicArchives> pageInfo = new PageInfo<BasBasicArchives>(carchives);
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
        List<BasBasicArchiveSentry> allList = basBasicArchivesService.findAllList(Integer.parseInt(String.valueOf(bAid)));
        PageInfo<BasBasicArchiveSentry> pageInfo = new PageInfo<BasBasicArchiveSentry>(allList);
        String jsonString = JSON.toJSONString(allList);
        System.out.println(jsonString);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }

    /**
     *
     * 跳转新增基础档案
     */
     @RequestMapping("/basicArchivesAdd")
     public String basicArchivesAdd(HttpSession session){
         SyEmp emp =(SyEmp) session.getAttribute("emp");
         List<BasDeliveryStandard> bas = basDeliveryStandardService.addBind(emp.getEmpNo());
         String uname = bas.get(0).getUname();
         session.setAttribute("emp",emp);
         session.setAttribute("ename",emp.getEmpName());
         session.setAttribute("uname",uname);

         return "/pages/basicData/basicArchives_add";
     }

    /**
     * 新增基础档案
     */
    @RequestMapping("/addBasicArch")
    public String addBasicArch(BasBasicArchives basBasicArchives,HttpSession session){
        System.out.println(basBasicArchives);
        SyEmp emp =(SyEmp) session.getAttribute("emp");
        List<BasDeliveryStandard> bas = basDeliveryStandardService.addBind(emp.getEmpNo());
        int operationUnitID = bas.get(0).getOperationUnitID();
        int operatorID = bas.get(0).getOperatorID();
        basBasicArchives.setOperationUnitID(operationUnitID);
        basBasicArchives.setOperatorID(operatorID);
        basBasicArchivesService.addBasicArch(basBasicArchives);
        return "/pages/basicData/basicArchives";
    }


    /**
     * 跳转修改页面
     */
    @RequestMapping("/basicArchivesUpd/{id}")
    public String getUpd(@PathVariable("id") Integer id,HttpSession session){
        session.setAttribute("id",id);
        return "/pages/basicData/basicArchives_upd";
    }



    @RequestMapping("/queryBasArchById/{id}")
    @ResponseBody
    public String queryBasArchById(@PathVariable("id") Integer id){
        BasBasicArchives basBasicArchives = basBasicArchivesService.queryBasArchById(id);
        String s = JSON.toJSONString(basBasicArchives);
        return s;
    }

    /**
     * 修改基础档案
     */
    @RequestMapping("/updBasicArch")
    public String updBasicArch(BasBasicArchives basBasicArchives){
        basBasicArchivesService.updBasicArch(basBasicArchives);
        return "/pages/basicData/basicArchives";
    }

    /**
     * 跳转新增详细基础档案页面
     */
    @RequestMapping("/basicArchSeAdd/{parentID}")
    public String basicArchSeAdd(@PathVariable("parentID") Integer parentID,HttpSession session){
        System.out.println("parentID"+parentID);
        SyEmp emp =(SyEmp) session.getAttribute("emp");
        List<BasDeliveryStandard> bas = basDeliveryStandardService.addBind(emp.getEmpNo());
        String uname = bas.get(0).getUname();
        session.setAttribute("ename",emp.getEmpName());
        session.setAttribute("uname",uname);
        session.setAttribute("parentID",parentID);
        return "/pages/basicData/basicArchives_detail_add";
    }

    /**
     *新增详细基础档案页面
     */
    @RequestMapping("/addBasicArchSe")
    public String addBasicArchSe(BasBasicArchiveSentry basBasicArchiveSentry,HttpSession session){
        SyEmp emp =(SyEmp) session.getAttribute("emp");
        List<BasDeliveryStandard> bas = basDeliveryStandardService.addBind(emp.getEmpNo());
        int operationUnitID = bas.get(0).getOperationUnitID();
        int operatorID = bas.get(0).getOperatorID();
        basBasicArchiveSentry.setOperationUnitID(operationUnitID);
        basBasicArchiveSentry.setOperatorID(operatorID);
        basBasicArchivesService.addBasicArchSentry(basBasicArchiveSentry);
        return "/pages/basicData/basicArchives_detail_add";
    }

    /**
     * 跳转修改详细档案信息
     */
    @RequestMapping("/getUpdBasArchSen/{id}")
    public String getUpdBasArchSen(@PathVariable("id") Integer id,HttpSession session){
        session.setAttribute("id",id);
        return "/pages/basicData/basicArchives_detail_upd";
    }

    /**
     * 根据id查询详细档案信息
     */
    @RequestMapping("/queryBasArchSenById/{id}")
    @ResponseBody
    public String queryBasArchSenById(@PathVariable("id") Integer id){
        BasBasicArchiveSentry basBasicArchiveSentry = basBasicArchivesService.queryBasArSenById(id);
        String s = JSON.toJSONString(basBasicArchiveSentry);
        return s;
    }

    /**
     * 修改详细档案信息
     */
    @RequestMapping("/updBasicArchSen")
    public String updBasicArchSen(BasBasicArchiveSentry basBasicArchiveSentry){
        basBasicArchivesService.updBasicArchSentry(basBasicArchiveSentry);
        return "/pages/basicData/basicArchives_detail_upd";
    }

    /**
     * 作废
     */
    @RequestMapping("/delBasArSenById/{id}")
    @ResponseBody
    public String delBasArSenById(@PathVariable("id") Integer id){
       basBasicArchivesService.delBasArSenById(id);
       return "a";
    }








}