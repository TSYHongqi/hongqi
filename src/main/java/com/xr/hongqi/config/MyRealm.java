/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyRealm
 * Author:   TSYH
 * Date:     2019-12-09 13:39
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.xr.hongqi.config;

import com.xr.springboot.pojo.User;
import com.xr.springboot.service.ShiroService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2019-12-09
 * @since 1.0.0
 */

public class MyRealm extends AuthorizingRealm {
    private final static Logger logger=LoggerFactory.getLogger(AuthorizingRealm.class);

    @Autowired
    private ShiroService shiroService;

    /**
     * 获取权限信息，只有在身份验证成功后才调用此方法获取权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("来授权了");
        //获取用户名
        String username = (String)principals.getPrimaryPrincipal();
        System.out.println("username=============="+username);
        //new一个授权信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //给授权信息设置角色集合,只能放角色名
        authorizationInfo.setRoles(shiroService.listRoles(username));
        Set<String> strings = shiroService.listRoles(username);
        Iterator<String> iterator = strings.iterator();
        String str = (String)iterator.next();
        System.out.println("所有权限"+strings);

        //给授权信息设置权限集合
        //用户权限列表
        Set<String> permsList = shiroService.listPermissions(username);

        Set<String> permsSet = new HashSet<String>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        authorizationInfo.setStringPermissions(permsSet);
        //返回用户授权信息
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("来认证了");
        //获取用户名
        String username = (String)token.getPrincipal();
        //根据用户名获取User对象
        User user = shiroService.getUserByUsername(username);

        if(user == null) {
            //找不到账号
            throw new UnknownAccountException();
        }

        /*
         * new一个身份验证信息
         */
        logger.info("数据库的加密盐值"+user.getCredentialsSalt());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                user.getUserName(),
                //从数据库中查出的密文密码
                user.getPassword(),
                //credentialsSalt=username+salt
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                //realm名称
                getName()
        );
        Session session = getSession();
        //将当前用户放进session
        session.setAttribute("username", username);

        /*
         * 返回身份验证信息，将交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
         * CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配
         */
        return authenticationInfo;
    }


    /**
     * 获取shiro封装的session
     */
    private Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){

        }
        return null;
    }

    /**
     * 设置 realm的 HashedCredentialsMatcher
     */
    @PostConstruct
    public void setHashedCredentialsMatcher() {
        this.setCredentialsMatcher(hashedCredentialsMatcher());
    }

    /**
     * 凭证匹配 : 指定 加密算法,散列次数
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }


}