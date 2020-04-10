package com.hongqi.springboot.controller.dispatch;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongqi.springboot.config.PasswordHelper;
import com.hongqi.springboot.model.*;
import com.hongqi.springboot.service.acceptance.BusinessAcceptanceService;
import com.hongqi.springboot.service.dispatch.AccWorkorderService;
import lombok.extern.slf4j.Slf4j;
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
 * @author TSYH
 * @create 2020-01-17
 * @since 1.0.0
 */
@Slf4j
@Controller
public class AccWorkorderController {

    @Autowired
    private AccWorkorderService accWorkorderService;


    /**
     * 跳转查台转单
     */
    @RequestMapping("/checkTable")
    public String getYeMian(){
        return "/pages/dispatch/checkTable";
    }


    @RequestMapping("/findCheckTable")
    @ResponseBody
    public String findCheckTable(@RequestParam(value="page",required=false) String page,
                                 @RequestParam(value="limit",required=false) String limit,
                                 @RequestParam(value = "queryPwd",required = false) String queryPwd,
                                 @RequestParam(value = "phone",required = false) String phone,
                                 @RequestParam(value = "businessNoticeNo",required = false) String businessNoticeNo,
                                 @RequestParam(value = "workGenerationTime",required = false) String workGenerationTime,
                                 @RequestParam(value = "empNo",required = false) String empNo,
                                 @RequestParam(value = "shortMessageint",required = false) String shortMessageint){
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<AccWorkOrder> accWorkOrders = accWorkorderService.queryAllWorkOrder(businessNoticeNo,queryPwd,phone,workGenerationTime,empNo,shortMessageint);
        PageInfo<AccWorkOrder> pageInfo = new PageInfo<AccWorkOrder>(accWorkOrders);
        String jsonString = JSON.toJSONString(accWorkOrders);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }
    /**
     * 查询查台密码
     */
    @RequestMapping("/getQueryPwd")
    @ResponseBody
    public String getQueryPwd(HttpSession session,String firstpwd){
        SyEmp emp = (SyEmp)session.getAttribute("emp");
        String queryPwd = PasswordHelper.getQueryPwd(emp.getQueryPwd());
        if(firstpwd.equals(queryPwd)){ //查台密码输入正确
            log.info("输入正确！");
            return "a";
        }else{
            return "b";
        }
    }

    /**
     * 销单
     */
    @RequestMapping("/destroyWork")
    @ResponseBody
    public String destroyWork(@RequestParam("businessNoticeNo") String businessNoticeNo){
      accWorkorderService.updateDistory(businessNoticeNo);
       return "a";
    }


    /**
     *跳转快速录入工作单界面
     */
    @RequestMapping("/getWorksheetQuickInput")
    public String getWorksheetQuickInput(){

        return "/pages/acceptance/worksheetQuickInput";
    }

    /**
     * 判断查找工作单中是否存在业务员通知单，或者工作单号
     */
    @RequestMapping("/IfbusNotiNo")
    @ResponseBody
    public String g(@RequestParam(value = "businessNoticeNo",required = false) String businessNoticeNo,
                    @RequestParam(value = "workSheetNo",required = false) String workSheetNo){
        AccWorkSheet accWorkSheet = accWorkorderService.queryAcWoShIfBusNo(businessNoticeNo,workSheetNo);
        AccBusinessAdmissibility accBusinessAdmissibility = accWorkorderService.queryAcWoNoIfBusNo(businessNoticeNo);
        if(accWorkSheet!=null){
            return "b";
        }else if (accBusinessAdmissibility==null){
            return "a";
        }else{
            return JSON.toJSONString(accBusinessAdmissibility);
        }
    }

    /**
     * 新增工作单
     * @param accWorkSheet
     * @return
     */
    @RequestMapping("/addAccWorksheet")
    @ResponseBody
    public String addAccWorksheet(AccWorkSheet accWorkSheet){
        String businessNoticeNo = accWorkSheet.getBusinessNoticeNo(); //通知单
        String destination = accWorkSheet.getDestination(); //目的地
        double weight = accWorkSheet.getWeight();//体重
        int total = accWorkSheet.getTotal();//总件数
        AccBusinessAdmissibility accBusinessAdmissibility = accWorkorderService.queryAcWoNoIfBusNo(businessNoticeNo);
        String sendAddress = accBusinessAdmissibility.getSendAddress();
        String county = sendAddress.substring(0,sendAddress.indexOf('区') + 1);
        String stowageRequirements = accWorkSheet.getStowageRequirements();
        if(stowageRequirements.equals("1")){
             accWorkSheet.setStowageRequirements("无");
        }else if(stowageRequirements.equals("2")){
            accWorkSheet.setStowageRequirements("禁航");
        }else{
            accWorkSheet.setStowageRequirements("禁铁路");
        }
        accBusinessAdmissibility.setBusinessNoticeNo(businessNoticeNo);
        accBusinessAdmissibility.setSendAddress(county+""+destination);
        accBusinessAdmissibility.setPackagesNum(total);
        accWorkorderService.updAccBusiness(accBusinessAdmissibility);
        accWorkorderService.addAccWorksheet(accWorkSheet);
        return "a";
    }


    @RequestMapping("/getWorksheetQuery")
    public String getWorksheetQuery(){

        return "/pages/acceptance/worksheetQuery";
    }

    /**
     * 查询工作单
     */
    @RequestMapping("/findAccWorkSheet")
    @ResponseBody
    public String findAccWorkSheet(@RequestParam(value="page",required=false) String page, @RequestParam(value="limit",required=false) String limit,
                                   @RequestParam(value="workGenerationTime",required=false) String workGenerationTime,@RequestParam(value="workSheetNo",required=false) String workSheetNo,
                                   @RequestParam(value="packagesNum",required=false) String packagesNum,@RequestParam(value="empNo",required=false)  String empNo,
                                   @RequestParam(value="disabled",required=false)  String disabled
            ,@RequestParam(value="name",required=false)  String name) {
      PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
      List<AccWorkSheet> accWorkSheets = accWorkorderService.queryAcc(workGenerationTime,workSheetNo,packagesNum,empNo,name,disabled);
      PageInfo<AccWorkSheet> pageInfo = new PageInfo<AccWorkSheet>(accWorkSheets);
      String jsonString = JSON.toJSONString(accWorkSheets);
      String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
      return jso;


    }

    /**
     * 跳转编辑
     */
    @RequestMapping("/dispatchingPersonnelSetEdi/{id}")
    public String dispatchingPersonnelSetEdi(@PathVariable("id") Integer id, HttpSession session){
        session.setAttribute("id",id);
        return "/pages/acceptance/worksheetQuery_upd";
    }

    /**
     * 根据id查询工作单
     */
    @RequestMapping("/queryAccWorShById")
    @ResponseBody
    public String queryAccWorShById(@RequestParam("id") Integer id){
        AccWorkSheet accWorkSheet = accWorkorderService.queryAccWorShById(id);
        String s = JSON.toJSONString(accWorkSheet);
        return s;
    }

    /**
     * 修改工作单
     */
    @RequestMapping("/worksheetQueryUpd")
    public String worksheetQueryUpd(AccWorkSheet accWorkSheet){
        String stowageRequirements = accWorkSheet.getStowageRequirements();
        if(stowageRequirements.equals("1")){
            accWorkSheet.setStowageRequirements("无");
        }else if(stowageRequirements.equals("2")){
            accWorkSheet.setStowageRequirements("禁航");
        }else{
            accWorkSheet.setStowageRequirements("禁铁路");
        }
        accWorkorderService.worksheetQueryUpd(accWorkSheet.getProductTime(),accWorkSheet.getStowageRequirements(),accWorkSheet.getId());
        return "/pages/acceptance/worksheetQuery_upd";
    }

    /**
     * 作废
     */
    @RequestMapping("/delWorksheet/{id}")
    @ResponseBody
    public String dispatPerSetDetail(@PathVariable("id") Integer id){
        accWorkorderService.updWorksheetQuery(id);

        return "a";
    }

    /**
     * 跳转查看详情
     */
    @RequestMapping("/workSheDetail/{id}")
    public String checkDetail(@PathVariable("id") Integer id,HttpSession session){
        session.setAttribute("id",id);
        return "/pages/acceptance/worksheetQuery_detail";
    }




























}



