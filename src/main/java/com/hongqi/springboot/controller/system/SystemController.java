/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SystemController
 * Author:   TSYH
 * Date:     2020-01-19 10:19
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.controller.system;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongqi.springboot.config.PasswordHelper;
import com.hongqi.springboot.model.*;
import com.hongqi.springboot.service.system.SystemService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-19
 * @since 1.0.0
 */
@Controller
public class SystemController {

    @Autowired
    private SystemService systemService;

    @RequestMapping("/sysUnit")
    public String getUnit(){

        return "/pages/systemManagement/sysUnit";
    }

    @RequestMapping("/findSyUnits")
    @ResponseBody
    public String findSyUnits(@RequestParam("page") String page, @RequestParam("limit") String limit,@RequestParam(value="name",required=false) String name){
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<SyUnits> syUnits = systemService.queryUnit(name);
        PageInfo<SyUnits> pageInfo = new PageInfo<SyUnits>(syUnits);
        String jsonString = JSON.toJSONString(syUnits);
        System.out.println(jsonString);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }

    /**
     * 新增跳转
     * @return
     */
    @RequestMapping("/sysUnitAdd")
    public String sysUnitAdd(){
        return "/pages/systemManagement/sysUnit_add";
    }

    /**
     * 新增
     */
    @RequestMapping("/addSysUnit")
    @ResponseBody
    public String add(@Param("name") String name, @Param("remarks") String remarks, HttpSession session){
        SyEmp emp = (SyEmp)session.getAttribute("emp");
        systemService.addSysUnit(name,remarks,emp.getId());
        return "/pages/systemManagement/sysUnit";
    }


    /**
     * 跳转到修改页面
     */
    @RequestMapping("/sysUnitUpd/{id}")
    public String getUpd(@PathVariable("id") Integer id,HttpSession session){
        session.setAttribute("id",id);
        return "/pages/systemManagement/sysUnit_upd";
    }


    /**
     * 修改
     */
    @RequestMapping("/updSysUnit")
    public String upd(String name,String remarks,Integer id){
        systemService.updUnit(name,remarks,id);
        return "/pages/systemManagement/sysUnit";
    }

    /**
     * 根据id查询
     */
    @RequestMapping("/querySysUnitById")
    @ResponseBody
    public String queryById(Integer id){
        SyUnits syUnits = systemService.queryById(id);
        String s = JSON.toJSONString(syUnits);
        return s;
    }

    /**
     * 删除
     */
    @RequestMapping("/delSysUnit")
    @ResponseBody
    public String delSysUnit(@RequestParam("id") Integer id) {
        systemService.delUnit(id);
        return  "a";
    }


    /**
     * 跳转员工管理
     */
    @RequestMapping("/sysEmp")
    public String sysEmp(){
        return "/pages/systemManagement/sysEmp";
    }


    /**
     *查询所有员工管理
     * ,@RequestParam(value="name",required=false) String name
     */
    @RequestMapping("/findSyEmp")
    @ResponseBody
    public String getSysEmp(@RequestParam("page") String page, @RequestParam("limit") String limit,@RequestParam(value="empName",required=false) String empName,@RequestParam(value = "disabled",required = false) String disabled){
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        Integer i = null;
        if(disabled!=null&&disabled!=""){
            i = Integer.parseInt(disabled);
        }
        List<SyEmp> syEmps = systemService.findAllEmp(i,empName);
        PageInfo<SyEmp> pageInfo = new PageInfo<SyEmp>(syEmps);
        String jsonString = JSON.toJSONString(syEmps);
        System.out.println(jsonString);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }
    /**
     * 跳转新增员工管理
     */
    @RequestMapping("/getAddEmp")
    public String getAddEmp(HttpSession session){
        List<SyUnits> syUnits = systemService.queryUnit(null);
        List<SyRole> sysRole = systemService.findSysRole(null, null);
        session.setAttribute("syUnits",syUnits);
        session.setAttribute("sysRole",sysRole);
        int i = systemService.queryMaxEmpNo();
        i = i+1;
        session.setAttribute("empNoi",i);
        return "/pages/systemManagement/sysEmp_add";
    }

    /**
     * 新增员工
     */
    @RequestMapping("/addEmp")
    public String addEmp(SyEmp syEmp){
        int empNo = systemService.queryMaxEmpNo();
        empNo = empNo+1;
        syEmp.setEmpNo(empNo+"");
        PasswordHelper passwordHelper =new PasswordHelper();
        SyEmp emp = passwordHelper.encryptPassword(syEmp); //加密

        //新增
        systemService.addEmp(emp);
        return "/pages/systemManagement/sysEmp";
    }

    /**
     * 跳转修改员工管理
     */
    @RequestMapping("/getUpdEmp/{bid}")
    public String getUpdEmp(@PathVariable("bid") Integer bid,HttpSession session){
        SyEmp emp = (SyEmp)session.getAttribute("emp");
        session.setAttribute("empid",bid); //选择的id
        session.setAttribute("empid2",emp.getId());//登录的用户id
        List<SyUnits> syUnits = systemService.queryUnit(null);
        List<SyRole> sysRole = systemService.findSysRole(null, null);
        session.setAttribute("syUnits",syUnits);
        session.setAttribute("sysRole",sysRole);

        return "/pages/systemManagement/sysEmp_edit";
    }

    /**
     * 修改员工
     */
    @RequestMapping("/updEmp")
    public String updEmp(SyEmp syEmp){
        if((syEmp.getPwd()!=null&&!syEmp.getPwd().equals(""))){
        SyEmp s= systemService.findEmpById(syEmp.getId());
        PasswordHelper passwordHelper = new PasswordHelper();
        syEmp.setEmpNo(s.getEmpNo());
        SyEmp emp = passwordHelper.encryptPassword(syEmp); //加密
        systemService.updEmp(emp);
        }else{
            systemService.updEmp(syEmp);
        }

        return "/pages/systemManagement/sysEmp";
    }

    /**
     * 根据id查询员工
     */
    @RequestMapping("/getUpdEmp")
    @ResponseBody
    public String getUpdEmp(@Param("id") Integer id){
        SyEmp empById = systemService.findEmpById(id);
        String s = JSON.toJSONString(empById);

        return s;
    }
    /**
     * 删除员工
     */
    @RequestMapping("/delEmp/{id}")
    @ResponseBody
    public String delEmp(@PathVariable("id") Integer id,HttpSession session){
        SyEmp emp = (SyEmp)session.getAttribute("emp");
        if(emp.getId()==id){
            return "b";
        }else {
            systemService.delEmpById(id);
            return "a";
        }
    }



    /**
     * 跳转栏目管理
     */
    @RequestMapping("/sysMenu")
    public String sysMenu(HttpSession session){
        List<SyMemus> pre = systemService.findPre();
        session.setAttribute("pre",pre);

        return "/pages/systemManagement/sysMenu";
    }

    /**
     * 查找所有栏目管理
     * @param page
     * @param limit
     * @param parentID
     * @param text
     * @return
     */
    @RequestMapping("/findSyMenu")
    @ResponseBody
    public String getSysMenu(@RequestParam("page") String page, @RequestParam("limit") String limit,
                             @RequestParam(value="parentID",required=false) String parentID,@RequestParam(value = "text",required = false) String text){
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        Integer i = null;
        if(parentID!=null&&parentID!=""){
            i = Integer.parseInt(parentID);
        }
        List<SyMemus> allMenu = systemService.findAllMenu(text, i);
        PageInfo<SyMemus> pageInfo = new PageInfo<SyMemus>(allMenu);
        String jsonString = JSON.toJSONString(allMenu);
        System.out.println(jsonString);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }


    /**
     * 跳转角色管理
     * @return
     */
    @RequestMapping("/sysRole")
    public String getRole(){
        return "/pages/systemManagement/sysRole";
    }


    /**
     * 查找角色管理
     */
    @RequestMapping("/findSysRole")
    @ResponseBody
    public String sysRole(@RequestParam("page") String page, @RequestParam("limit") String limit,
                          @RequestParam(value="roleName",required=false) String roleName,@RequestParam(value = "disabled",required = false) String disabled){
        Integer i = null;
        if(disabled!=null&&disabled!=""){
            i = Integer.parseInt(disabled);
        }
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<SyRole> sysRole = systemService.findSysRole(i,roleName);
        PageInfo<SyRole> pageInfo = new PageInfo<SyRole>(sysRole);
        String jsonString = JSON.toJSONString(sysRole);
        System.out.println(jsonString);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }

    /**
     * 跳转新增栏目管理
     */
    @RequestMapping("/sysMenuAdd")
    public String getAddUnit(HttpSession session){
        List<SyMemus> pre2 = systemService.findPre2();
        session.setAttribute("pre2",pre2);
        return "/pages/systemManagement/sysMenu_add";
    }

    /**
     * 新增菜单
     */
    @RequestMapping("/addMenus")
    public String addMenus(SyMemus syMemus){

        systemService.addMenu(syMemus);
        return "/pages/systemManagement/sysMenu_add";
    }

    /**
     * 跳转修改菜单
     */
    @RequestMapping("/sysMenuEdit/{id}")
    public String getUpdMenu(@PathVariable("id") Integer id,HttpSession session){
        session.setAttribute("mid",id);
        List<SyMemus> pre2 = systemService.findPre2();
        session.setAttribute("pre2",pre2);
        List<SyMemus> syMemus = systemService.queryLastMenu();

        return "/pages/systemManagement/sysMenu_edit";
    }

    /**
     * 根据id查询菜单信息
     */
    @RequestMapping("/querySysMenuById")
    @ResponseBody
    public String querySysMenuBy(Integer id){
        SyMemus syMemus = systemService.querySysMenuById(id);
        String s = JSON.toJSONString(syMemus);
        return s;
    }

    /**
     * 修改菜单
     */
    @RequestMapping("/updMenus")
    public String updMenus(SyMemus syMemus){
        systemService.updMenu(syMemus);
        return "/pages/systemManagement/sysMenu_edit";
    }

    /**
     * 删除菜单
     */
    @RequestMapping("/delSysMenu/{id}")
    @ResponseBody
    public String delSysMenu(@PathVariable("id") Integer id) {
        SyMemus menuIfDel = systemService.findMenuIfDel(id);
        if (menuIfDel == null) {
            return "b";
        } else {
            systemService.delMenu(id);
            return "a";
        }
    }

    /**
     * 跳转新增角色页面
     */
    @RequestMapping("/sysRoleAdd")
    public String getsysRoleAdd(){
       return "/pages/systemManagement/sysRole_add";
    }

    /**
     * 新增角色
     */
    @RequestMapping("/addRole")
    @ResponseBody
    public String addRole(SyRole syRole){
        systemService.addRole(syRole);
        return "/pages/systemManagement/sysRole";
    }

    /**
     * 跳转修改角色页面
     */
    @RequestMapping("/sysRoleUpd/{id}")
    public String getsysRoleUpd(HttpSession session,@PathVariable("id") Integer id){
        SyEmp emp = (SyEmp) session.getAttribute("emp");
        int roleID = emp.getRoleID();
        session.setAttribute("roleID",roleID);//本用户的id
        session.setAttribute("rid",id);//选中的id
        return "/pages/systemManagement/sysRole_upd";
    }

    /**
     * 根据id查询角色
     */
    @RequestMapping("/querySysRoleById")
    @ResponseBody
    public String querySysRoleById(Integer id){
        SyRole roleById = systemService.findRoleById(id);
        String s = JSON.toJSONString(roleById);
        return s;
    }

    /**
     * 修改角色
     */
    @RequestMapping("/updRole")
    public String updRole(SyRole syRole){
        systemService.updRole(syRole);
        return "/pages/systemManagement/sysRole";
    }



    /**
     * 删除角色
     */
    @RequestMapping("/delSysRole")
    public String delSysRole(Integer id){
        systemService.delRole(id);
        return "/pages/systemManagement/sysRole";
    }

    /**
     * 跳转分配页面
     */
    @RequestMapping("/sysRoleDistribution/{id}")
    public String sysRoleDistribution(@PathVariable("id") Integer id,HttpSession session){
        List<SyMemus> pre2 = systemService.findPre2();
        session.setAttribute("pre2",pre2);
        session.setAttribute("id",id);
        return "/pages/systemManagement/sysRole_distribution";
    }

    /**
     * 分配权限（获得三级栏目）
     */
    @RequestMapping("/getTwoMenu")
    @ResponseBody
    public String getRolePerm(Integer parentID){
        List<SyMemus> syMemus = systemService.queryMenuByP(parentID);
        String s = JSON.toJSONString(syMemus);
        return s;
    }

    @RequestMapping("/getCheckById")
    @ResponseBody
    public String getCheckById(Integer id){
        List<SyMemus> syMemus = systemService.queryPerByRId(id);
        String s = JSON.toJSONString(syMemus);
        return s;
    }

    @RequestMapping("/getBaoCun/{a}/{id}")
    @ResponseBody
    public String getBaoCun(@PathVariable("a") String a,@PathVariable("id") Integer id){
        List<SyMemus> syMemus = systemService.queryPerByRId(id);
        //定义一个数组1，存储数据库里面的值
        List<String> list1 = new ArrayList();
        for (SyMemus syMemu : syMemus) {
            list1.add(syMemu.getId().toString());
        }
        //定义一个数组2
        List<String> list2 = new ArrayList();
        String[] split = a.split(","); //将后台得到的字符串存到数组中
        String[] strings = replaceNull(split); //去除空格
        for (String string : strings) {
            list2.add(string);
        }
        if(!list2.get(0).equals("")) {
            if(!(list1.contains("1")&&list1.contains("2")&&list1.contains("3"))){
               //新增
                systemService.addSyRM(id, 1);
                systemService.addSyRM(id, 2);
                systemService.addSyRM(id, 3);
            }
            for (String o : list2) {
                if (!list1.contains(o)) {
                    // 新增数据库里面没有的
                    systemService.addSyRM(id,Integer.parseInt(o));
                }
            }
            for (String o : list1) {
                if (!list2.contains(o)) {
                    // 删除数据库里面多余的
                    systemService.delSyRMByMid(Integer.parseInt(o),id);
                }
            }
        }else{
            return "b"; //当选择的为空！
        }
        return "a";
    }

    /**
     * 消除数组中的空值
     */
    private String[] replaceNull(String[] str){
        //用StringBuffer来存放数组中的非空元素，用“;”分隔
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<str.length; i++) {
            if("".equals(str[i])) {
                continue;
            }
            sb.append(str[i]);
            if(i != str.length - 1) {
                sb.append(";");
            }
        }
        //用String的split方法分割，得到数组
        str = sb.toString().split(";");
        return str;
    }










}