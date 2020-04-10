/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ShiroSessionManager
 * Author:   TSYH
 * Date:     2020-04-08 15:15
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-04-08
 * @since 1.0.0
 */
@Slf4j
public class ShiroSessionManager extends DefaultWebSessionManager {

    /**
     * 客户端request的header中,自定义属性的名称是token(值是sessionid)
     */
    public final static String HEADER_TOKEN_NAME = "token";
    //给ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE设置的值,值可随意,但是根据之前的源码,我们不能取cookie和url等字段,取mystateless,无状态的,应该不会和源码冲突
    private static final String REFERENCED_SESSION_ID_SOURCE = "mystateless";
    //Stateless request
    /**
     * 重写getSessionId,分析请求头中的指定参数，做用户凭证sessionId
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response){
        String id = WebUtils.toHttp(request).getHeader(HEADER_TOKEN_NAME);
        if(StringUtils.isEmpty(id)){
            //如果没有携带id参数则按照默认方式获取,事实上,只有登录接口和一些不需要游客身份使用的接口以外,没有任何接口会走这个逻辑，因为我们已经没有了cookie
            //  System.out.println("super："+super.getSessionId(request, response));
            return super.getSessionId(request, response);
        }else{
            //如果请求头中有 token ，则参照源码设置三项
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return id;
        }
    }

}