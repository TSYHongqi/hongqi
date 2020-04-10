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
import com.hongqi.springboot.model.SyEmp;
import com.hongqi.springboot.service.basic.BasDeliveryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


    /**
     * 查找所有收派标准
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findDeliveryStandard")
    @ResponseBody
    public String findDeliveryStandard(@RequestParam(value="page",required=false) String page, @RequestParam(value="limit",required=false) String limit,
        @RequestParam(value="name",required=false) String name,@RequestParam(value="minWeight",required=false) String minWeight,
                                       @RequestParam(value="maxWeight",required=false) String maxWeight,@RequestParam(value="empName",required=false) String empName,
                                       @RequestParam(value="invalidateSign",required=false) String invalidateSign,@RequestParam(value="operationTime",required=false) String operationTime) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        BasDeliveryStandard b = new BasDeliveryStandard();
        b.setEmpName(empName);
        b.setName(name);
        if(invalidateSign!=""&&invalidateSign!=null) {
            b.setInvalidateSign(Integer.parseInt(invalidateSign));
        }
        if(operationTime!=""&&operationTime!=null){
            Date parse = sf.parse(operationTime);
            b.setOperationTime(parse);
        }
        if(maxWeight!=""&&maxWeight!=null){
            b.setMaxWeight(Double.parseDouble(maxWeight));
        }
        if(minWeight!=""&&minWeight!=null) {
            b.setMinWeight(Double.parseDouble(minWeight));
        }
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<BasDeliveryStandard> standards = basDeliveryStandardService.findStandards(b);
        PageInfo<BasDeliveryStandard> pageInfo = new PageInfo<BasDeliveryStandard>(standards);
        String jsonString = JSON.toJSONString(standards);

        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }

    /**
     * 跳转页面修改
     * @param bid
     * @return
     */
    @RequestMapping("/getUpdate/{bid}")
    public String getUpdate(@PathVariable("bid") Integer bid, HttpSession session){
        session.setAttribute("id",bid);
        return "/pages/basicData/deliveryStandard_update";
    }

    @RequestMapping("/queryById/{id}")
    @ResponseBody
    public String queryById(@PathVariable("id") String id){
        BasDeliveryStandard basDeliveryStandard = basDeliveryStandardService.queryById(Integer.parseInt(id));
        String jsonString = JSON.toJSONString(basDeliveryStandard);
        return jsonString;
    }
    /**
     * 修改用户
     */
    @RequestMapping("/update")
    public String update(BasDeliveryStandard basDeliveryStandard){
        basDeliveryStandardService.updateStandards(basDeliveryStandard);
        return "/pages/basicData/deliveryStandard";
    }


    /**
     * 作废标签
     * @param employeesId
     * @return
     */
    @RequestMapping("/ifDelAll/{employeesId}")
    public String ifDelAll(@PathVariable("employeesId") String employeesId){
        List<String> list = new ArrayList<>();
        String[] strs = employeesId.split(",");
        for (String str : strs) {
            list.add(str);
        }
        basDeliveryStandardService.updateInvalidateSign(list);

        return "/pages/basicData/deliveryStandard";
    }

    /**
     *跳转新增页面新增
     */
    @RequestMapping("/addHtml")
    public String addHtml(HttpSession session){
        SyEmp emp =(SyEmp) session.getAttribute("emp");
        List<BasDeliveryStandard> bas = basDeliveryStandardService.addBind(emp.getEmpNo());
        String uname = bas.get(0).getUname();
        session.setAttribute("emp",emp);
        session.setAttribute("ename",emp.getEmpName());
        session.setAttribute("uname",uname);
        return "/pages/basicData/deliveryStandard_add";
    }

    /**
     * 新增
     */
    @RequestMapping("/addStanstard")
     public String  addStanstard(BasDeliveryStandard basDeliveryStandard,HttpSession session){
        SyEmp emp =(SyEmp) session.getAttribute("emp");
        List<BasDeliveryStandard> bas = basDeliveryStandardService.addBind(emp.getEmpNo());
        int operationUnitID = bas.get(0).getOperationUnitID();
        int operatorID = bas.get(0).getOperatorID();
        basDeliveryStandard.setOperationUnitID(operationUnitID);
        basDeliveryStandard.setOperatorID(operatorID);
        basDeliveryStandardService.addStandards(basDeliveryStandard);
        return "/pages/basicData/deliveryStandard";
     }





























}