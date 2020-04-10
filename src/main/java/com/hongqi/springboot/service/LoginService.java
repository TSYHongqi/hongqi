/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: LoginService
 * Author:   TSYH
 * Date:     2020-01-04 21:44
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service;

import com.hongqi.springboot.model.SyEmp;
import com.hongqi.springboot.model.SyMemus;

import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-04
 * @since 1.0.0
 */

public interface LoginService {
    /**
     * 根据用户名查找用户
     */
    SyEmp getEmpByEmpname(String empNo);

    /*
     *根据用户查询角色
     */
    Set<String> listRoles(String empNo);

    /**
     * 查询用户所有权限,返回值是权限名集合
     */
    Set<String> listPermissions(String empNo,Integer roleID);
    /**
     * 一级目录
     */
    List<SyMemus> getOneMenu(String empNo);

    /**
     * 二级目录
     */
    List<SyMemus> getTowMenu(int id,String empNo);

    /**
     * 三级目录
     */
    List<SyMemus> getThreeMenu(int id,String empNo);

}