/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: signInputController
 * Author:   TSYH
 * Date:     2020-02-07 12:43
 * Description: 签收录入
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.controller.dispatch;

import com.alibaba.fastjson.JSON;
import com.hongqi.springboot.model.*;
import com.hongqi.springboot.service.basic.BasDeliveryStandardService;
import com.hongqi.springboot.service.dispatch.SingnInputService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈签收录入〉
 *
 * @author TSYH
 * @create 2020-02-07
 * @since 1.0.0
 */
@Controller
public class SignInputController {

    @Autowired
    private SingnInputService singnInputService;

    @Autowired
    private BasDeliveryStandardService basDeliveryStandardService;

    /**
     * 跳转签收录入页面
     */
    @RequestMapping("/signInput")
    public String getsignInput(){
        return "/pages/dispatch/signInput";
    }

    /**
     * 查询签收录入页面
     */
    @RequestMapping("/findsignInput")
    @ResponseBody
    public String findsignInput(@RequestParam(value="page",required=false) String page,
                                @RequestParam(value="limit",required=false) String limit,
                                @RequestParam(value = "empName",required = false) String empName,
                                @RequestParam(value = "workSheetNo",required = false) String workSheetNo,
                                @RequestParam(value = "start",required = false) String start,
                                @RequestParam(value = "end",required = false) String end,
                                @RequestParam(value = "signType",required = false) String signType,
                                @RequestParam(value = "courierint",required = false) String courierint,
                                @RequestParam(value = "name",required = false) String name,
                                @RequestParam(value = "invalidateMark",required = false) String invalidateMark){
        return singnInputService.findAllSingnInput(page,limit,empName, workSheetNo, start, end, signType, courierint, name, invalidateMark);
    }

    /**
     * 跳转新增签收录入页面
     */
    @RequestMapping("/signInputAdd")
    public String signInputAdd(HttpSession session){
        SyEmp emp = (SyEmp)session.getAttribute("emp");
        List<BasDeliveryStandard> basDeliveryStandards = basDeliveryStandardService.addBind(emp.getEmpNo());
        session.setAttribute("empName",emp.getEmpName());
        session.setAttribute("uname",basDeliveryStandards.get(0).getUname());
        return "/pages/dispatch/signInput_add";
    }

    /**
     * 跳转取消签收页面
     */
    @RequestMapping("/cancelSignAppCon")
    public String cancelSignAppCon(){

        return "/pages/dispatch/cancelSignApplicationConfirmation";
    }

    /**
     * 查询取消签收页面
     */
    @RequestMapping("/findCancelSignAppCon")
    @ResponseBody
    public String findCancelSignAppCon(@RequestParam(value="page",required=false) String page,
                                       @RequestParam(value="limit",required=false) String limit,
                                       @RequestParam(value = "appNo",required = false) String appNo,
                                       @RequestParam(value = "workSheetNo",required = false) String workSheetNo,
                                       @RequestParam(value = "status",required = false) String status,
                                       @RequestParam(value = "why",required = false) String why,
                                       @RequestParam(value = "appPeople",required = false) String appPeople,
                                       @RequestParam(value = "appTime",required = false) String appTime,
                                       @RequestParam(value = "appUnit",required = false) String appUnit){

     return singnInputService.queryDisCancelSign(page, limit, appNo, workSheetNo, status, why, appPeople, appTime, appUnit);
    }

    /**
     * 跳转新增取消签收页面
     */
    @RequestMapping("/cancelAppConAdd")
    public String cancelAppConAdd(HttpSession session){
        String s = singnInputService.queryMaxAppNo();
        if(s!=null) {
            String sqd = s.substring(3);
            int i = Integer.parseInt(sqd) + 1;
            session.setAttribute("sqd", "SQD" + i);
        }else{
            session.setAttribute("sqd", "SQD10001");
        }
        return "/pages/dispatch/cancelSignApplicationConfirmation_add";
    }

    /**
     * 新增取消签收页面
     */
    @RequestMapping("/addCancelSignAppCon")
    public String addCancelSignAppCon(DisCancelSign disCancelSign,HttpSession session){
        //新增
        singnInputService.addDisCancelSign(disCancelSign,session);
        return "/pages/dispatch/cancelSignApplicationConfirmation";
    }

    /**
     * 验证
     */
    @RequestMapping("/IfWorkSheetNo")
    @ResponseBody
    public String ifWorkSheetNo(String workSheetNo){
        DisWorkordersign disWorkordersign = singnInputService.queryAllDis(workSheetNo);
        if(disWorkordersign==null){
            return "a";
        }else{
            return "b";
        }

    }
    //
    @RequestMapping("/IfWorkSheetNo2")
    @ResponseBody
    public String queryIf(String workSheetNo){
        DisCancelSign disCancelSign = singnInputService.queryIf(workSheetNo);
        if(disCancelSign!=null){
            return "a";
        }else{
            return "b";
        }
    }

    @RequestMapping("/IfWorkSheetNo3")
    @ResponseBody
    public String IfWorkSheetNo3(String workSheetNo){
        AccWorkSheet accWorkSheet = singnInputService.queryIfaccWor(workSheetNo);
        if(accWorkSheet==null){
            return "a";
        }else{
            return "b";
        }
    }

    /**
     * 同意
     */
    @RequestMapping("/confirm/{id}")
    @ResponseBody
    public String confirm(@PathVariable("id") Integer id,HttpSession session){
        singnInputService.confirm(id,session);
        return "a";
    }

    /**
     * 跳转拒绝
     */
    @RequestMapping("/getRefuse/{id}")
    public String getRefuse(@PathVariable("id") Integer id,HttpSession session){
        session.setAttribute("id",id);
        return "/pages/dispatch/cancel_refuse";
    }

    /**
     * 拒绝
     */
    @RequestMapping("/refuse")
    public String refuse(@RequestParam("id") Integer id,@RequestParam("why") String why){
        singnInputService.refuse(id,why);
        return "/pages/dispatch/cancelSignApplicationConfirmation";
    }

    /**
     * 跳转详情页面
     */
    @RequestMapping("/cancelSignAppConDetail/{workSheetNo}")
    public String cancelSignAppConDetail(@PathVariable("workSheetNo") String workSheetNo,HttpSession session){
        session.setAttribute("workSheetNo",workSheetNo);
        return "/pages/dispatch/cancelSignApplicationConfirmation_detail";
    }

    /**
     * 详情
     */
    @RequestMapping("/queryAllByWoShNo")
    @ResponseBody
    public String queryAllByWoShNo(@RequestParam("workSheetNo") String workSheetNo){
        DisCancelSign disCancelSign = singnInputService.queryIf(workSheetNo);
        String s = JSON.toJSONString(disCancelSign);
        return s;
    }

    /**
     * 作废
     */
    @RequestMapping("/delDisCancelSign/{id}")
    @ResponseBody
    public String delDisCancelSign(@PathVariable("id") Integer id){
        singnInputService.delDisCancelSign(id);
        return "a";
    }

    /**
     * 员工是否存在
     */
    @RequestMapping("/ifEmpNo")
    @ResponseBody
    public String ifEmpNo(@RequestParam("empNo") String empNo){
        SyEmp syEmp = singnInputService.ifEmpNo(empNo);
        if(syEmp==null){
           //不存在
            return "a";
        }else{
            return "b";
        }
    }


    /**
     * 添加签收单
     */
    @RequestMapping("/addWorkorSign")
    public String addWorkorSign(@RequestParam("workSheetNo") String workSheetNo, @RequestParam("workOrderType") Integer workOrderType,
                                @RequestParam("signType") Integer signType, @RequestParam("signTime") String signTime,
                                @RequestParam("recipient") String  recipient, @RequestParam("courierint") String courierint,HttpSession session){
        try {
            singnInputService.addDisSign(workSheetNo,workOrderType,signType,signTime,recipient,courierint,session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/dispatch/signInput";
    }

    /**
     * 跳转修改页面
     */
    @RequestMapping("/signInputUpd/{workSheetNo}")
    public String signInputUpd(@PathVariable("workSheetNo") String workSheetNo,HttpSession session){
        //AccWorkSheet accWorkSheet = singnInputService.queryIfaccWor(workSheetNo);
        session.setAttribute("workSheetNo",workSheetNo);
        return "/pages/dispatch/signInput_upd";
    }


    /**
     * 根据workSheetNo查询
     */
    @RequestMapping("/queryByWorkSheetNo")
    @ResponseBody
    public String queryById(@RequestParam("workSheetNo") String workSheetNo){
        DisWorkordersign disWorkordersign = singnInputService.queryAllDis(workSheetNo);
        SyEmp syEmp = singnInputService.ifEmpNo(disWorkordersign.getInputPersonCode());
        disWorkordersign.setEmpName(syEmp.getEmpName()); //录入人姓名
        List<BasDeliveryStandard> basDeliveryStandards = basDeliveryStandardService.addBind(disWorkordersign.getInputPersonCode());
        String uname = basDeliveryStandards.get(0).getUname();
        disWorkordersign.setName(uname); //录入人单位
        List<BasDeliveryStandard> basDeliveryStandards1 = basDeliveryStandardService.addBind(disWorkordersign.getCourierint());//签收
        disWorkordersign.setUname(basDeliveryStandards1.get(0).getUname());//签收单位
        String s = JSON.toJSONString(disWorkordersign);
        return s;
    }

    /**
     * 查询输入的签收人是否在属于该单位
     */
    @RequestMapping("/queryUnits")
    @ResponseBody
    public String queryUnits(@Param("empNo") String empNo){
        String s = singnInputService.queryUnits(empNo);
        return s;
    }

    /**
     * 修改签收单
     */
    @RequestMapping("/updWorkorSign")
    public String updateSign(@RequestParam("recipient") String  recipient, @RequestParam("workSheetNo") String workSheetNo,
                             @RequestParam("signTime") String signTime){
        singnInputService.updateSign(recipient,signTime, workSheetNo);
        return "/pages/dispatch/signInput";
    }

    /**
     * 跳转签收详情页面
     */
    @RequestMapping("/getsignInputDetail/{workSheetNo}")
    public String get(@PathVariable("workSheetNo") String workSheetNo,HttpSession session){
        session.setAttribute("workSheetNo",workSheetNo);
        return "/pages/dispatch/signInput_detail";
    }


















}