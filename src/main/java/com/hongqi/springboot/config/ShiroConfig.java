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
package com.hongqi.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
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


    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;


    /**
     * 2.配置Shiro核心 安全管理器 SecurityManager
     * SecurityManager安全管理器：所有与安全有关的操作都会与SecurityManager交互；且它管理着所有Subject；负责与后边介绍的其他组件进行交互。（类似于SpringMVC中的DispatcherServlet控制器）
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将自定义的realm交给SecurityManager管理
        securityManager.setRealm(getMyRealm());
        //用户授权/认证信息Cache, 采用EhCache缓存
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        securityManager.setRememberMeManager(rememberMeManager());
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

        //这样就将自定义的拦截注入到authc中。
        //至此解决shiro拦截OPTIONS请求的bug。
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        // 注意这里不要用Bean的方式，否则会报错
        filters.put("authc", new ShiroUserFilter());
        shiroFilterFactoryBean.setFilters(filters);

        //2.配置拦截链
        //使用LinkedHashMap，因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        // Map<K,V>——K指的是拦截的url，V值的是该url是否拦截
        Map<String, String> filterChainMap = new LinkedHashMap<String, String>();
        //配置退出过滤器logout，由shiro实现
        // 注意：改为调用Service层从数据库中获取数据
        filterChainMap.put("/logout", "logout");
        //authc:所有url都必须认证通过才可以访问;
        // anon:所有url都都可以匿名访问，先配置anon再配置authc。
        filterChainMap.put("/statics/**","anon");
        filterChainMap.put("/login.html", "anon");
        filterChainMap.put("/doLogin","anon");
        filterChainMap.put("/","anon");
        filterChainMap.put("/getGifCode","anon");
        filterChainMap.put("/ifXiangDeng","anon");
        filterChainMap.put("/**", "authc");
        //3.设置默认登录的URL.
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //shiroFilterFactoryBean.setSuccessUrl("/main.html");
        //shiroFilterFactoryBean.setUnauthorizedUrl("/login.html");
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

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }
    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     * @return*/
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    /**
     * redisManager
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setPassword(password);
        // 配置过期时间
        redisManager.setExpire(180000);
        return redisManager;
    }

    /**
     * cacheManager
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * redisSessionDAO
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


    @Bean
    public ShiroSessionManager sessionManager() {
        ShiroSessionManager manager = new ShiroSessionManager();
        manager.setGlobalSessionTimeout(180000);
        manager.setDeleteInvalidSessions(true);
        manager.setSessionValidationSchedulerEnabled(false);

        //manager.setSessionValidationScheduler(sessionValidationScheduler);
        manager.setSessionDAO(redisSessionDAO());
        manager.setSessionIdCookieEnabled(true);
       //  manager.setSessionIdCookie(sessionIdCookie);
        manager.setSessionIdUrlRewritingEnabled(false);
        return manager;
    }




}

