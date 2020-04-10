/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: HelloController
 * Author:   TSYH
 * Date:     2020-01-04 10:41
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.controller;

import com.hongqi.springboot.model.SyEmp;
import com.hongqi.springboot.model.SyMemus;
import com.hongqi.springboot.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /*@RequestMapping({"/login","/"})
    public String get(){
        return "/pages/login";
    }
*/
    @RequestMapping("/doLogin")
    public String doLogin(SyEmp emp) {

        //得到Subject,通过SecurityUtils得到Subject，其会自动绑定到当前线程；如果在web环境在请求结束时需要解除绑定
        Subject subject = SecurityUtils.getSubject();
        //创建用户名/密码身份验证Token（即用户身份/凭证）
        System.out.println(emp.getEmpNo()+","+emp.getPwd());
        UsernamePasswordToken token = new UsernamePasswordToken(emp.getEmpNo(), emp.getPwd());
        token.setRememberMe(emp.isRememberMe());
        try {
            /*
             * 身份验证，调用subject.login方法进行登录，其会自动委托给SecurityManager.login方法进行登录
             * 通过login登录，如果登录失败将抛出相应的AuthenticationException，
             * 如果登录成功调用subject.isAuthenticated就会返回true，即已经通过身份验证
             * 如果isRemembered返回true，表示是通过记住我功能登录的而不是调用login方法登录的
             * isAuthenticated/isRemembered是互斥的，即如果其中一个返回true，另一个返回false
             */
            subject.login(token);
            System.out.println("认证成功:" + subject.isAuthenticated());

            return "redirect:/main.html";

        } catch (AuthenticationException e) {
            /*
             *  如果身份验证失败请捕获AuthenticationException或其子类，常见的如：
             *  DisabledAccountException（禁用的帐号）、
             *  LockedAccountException（锁定的帐号）、
             *  UnknownAccountException（错误的帐号）、
             *  ExcessiveAttemptsException（登录失败次数过多）、
             *  IncorrectCredentialsException （错误的凭证）、
             *  ExpiredCredentialsException（过期的凭证）等，具体请查看其继承关系
             *  对于页面的错误消息展示，最好使用如“用户名/密码错误”而不是“用户名错误”/“密码错误”，防止一些恶意用户非法扫描帐号库
             */
            System.out.println("失败！！");
            return "/pages/login";
        }
    }


    @RequestMapping("/getMenu")
    @ResponseBody
    public String getMenu(HttpSession session){
        SyEmp emp = (SyEmp)session.getAttribute("emp");
        List<SyMemus> oneMenu = loginService.getOneMenu(emp.getEmpNo());
        String str ="[";
        for (SyMemus syMemus : oneMenu) {
            str+="{" +
                    "title:'"+syMemus.getText()+"'," +
                    "icon:'"+syMemus.getIcon()+"'," +
                    "isCurrent:"+syMemus.isCurrent()+"," +
                    "menu:[" ;
            Integer id = syMemus.getId();
            List<SyMemus> towMenu = loginService.getTowMenu(id,emp.getEmpNo());

            for(SyMemus memus:towMenu){
                            str+="{" +
                                    "title:'"+memus.getText()+"'," +
                                    "icon:'"+memus.getIcon()+"'," +
                                    "href:'"+memus.getUrl()+"'," +
                                    "isCurrent: "+memus.isCurrent()+"," +
                                    "children: [";
                            Integer id1 = memus.getId();
                            List<SyMemus> threeMenu = loginService.getThreeMenu(id1,emp.getEmpNo());
                                     for(SyMemus threeMenus:threeMenu){
                                        str +="{" +
                                                "title:'"+threeMenus.getText()+"'," +
                                                "href:'"+threeMenus.getUrl()+"'," +
                                                "isCurrent: "+threeMenus.isCurrent()+"" +
                                                "},";
                                     }
                                     str+="]";
                            str+="},";
                        }
                   str+= "]},";
        }
        str+="]";
        return str;
    }






}