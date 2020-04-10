/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ShiroUserFilter
 * Author:   TSYH
 * Date:     2020-04-08 17:31
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.config;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author TSYH
 * @create 2020-04-08
 * @since 1.0.0
 */
public class ShiroUserFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        //无条件放行OPTIONS
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            setHeader(httpRequest, httpResponse);
            return true;
        }
        return super.preHandle(request, response);
    }



    /**
     * 为response设置header，实现跨域
     */
    private void setHeader(HttpServletRequest request, HttpServletResponse response) {
        System.err.println("ShiroFilter");
//        System.err.println("setHeader -- " + request.getHeader("Access-Control-Request-Headers"));
        //跨域的header设置
//        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST,PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "content-type,x-requested-with,token");
//        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Allow-Headers") + ",token");
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }
}