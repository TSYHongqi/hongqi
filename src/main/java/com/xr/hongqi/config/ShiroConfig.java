/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ShiroConfig
 * Author:   TSYH
 * Date:     2019-12-09 13:00
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.xr.hongqi.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author TSYH
 * @create 2019-12-09
 * @since 1.0.0
 */
@Configuration
public class ShiroConfig {
    /**
     * 2.配置Shiro核心 安全管理器 SecurityManager
     * SecurityManager安全管理器：所有与安全有关的操作都会与SecurityManager交互；且它管理着所有Subject；负责与后边介绍的其他组件进行交互。（类似于SpringMVC中的DispatcherServlet控制器）
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将自定义的realm交给SecurityManager管理
        securityManager.setRealm(getMyRealm());
        return securityManager;
    }

    /**
     * 3.配置Shiro的Web过滤器
     * 拦截浏览器请求并交给SecurityManager处理
     */
    @Bean
    public ShiroFilterFactoryBean webFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //1.设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        //2.配置拦截链
        //使用LinkedHashMap，因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        // Map<K,V>——K指的是拦截的url，V值的是该url是否拦截
        Map<String, String> filterChainMap = new LinkedHashMap<String, String>();
        //配置退出过滤器logout，由shiro实现
        // 注意：改为调用Service层从数据库中获取数据
        filterChainMap.put("/logout", "logout");
        //authc:所有url都必须认证通过才可以访问;
        // anon:所有url都都可以匿名访问，先配置anon再配置authc。
        filterChainMap.put("/login", "anon");
        filterChainMap.put("/**", "anon");
        //3.设置默认登录的URL.
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 1.创建Realm
     */
    @Bean
    public MyRealm getMyRealm() {
        return new MyRealm();
    }

    /**
     * 用于thymeleaf模板使用shiro标签
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


}

