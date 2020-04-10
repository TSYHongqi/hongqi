/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: GetGifController
 * Author:   TSYH
 * Date:     2020-04-08 21:59
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.controller;

import com.hongqi.springboot.vcode.Captcha;
import com.hongqi.springboot.vcode.GifCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-04-08
 * @since 1.0.0
 */
@Controller
public class GetGifController {

    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @RequestMapping(value="getGifCode",method= RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(120,30,4);
            //输出
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);
            //存入Session

            session.setAttribute("_code",captcha.text().toLowerCase());
        } catch (Exception e) {
            System.err.println("获取验证码异常："+e.getMessage());
        }
    }

    @PostMapping("/ifXiangDeng")
    @ResponseBody
    public String ifXiangDeng(HttpSession httpSession,@RequestParam("vcode") String vcode){
        String code = (String)httpSession.getAttribute("_code");
        if(!vcode.toLowerCase().equals(code)){//不相等，验证码输入有误
            return "a";
        }
        return "b";
    }




}