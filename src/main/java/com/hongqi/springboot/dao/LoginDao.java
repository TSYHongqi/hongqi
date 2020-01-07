/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: LoginDao
 * Author:   TSYH
 * Date:     2020-01-05 2:29
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.dao;

import com.hongqi.springboot.model.SyEmp;
import com.hongqi.springboot.model.SyMemus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-05
 * @since 1.0.0
 */
@Repository
public interface LoginDao {
    /**
     * 根据用户名查找用户
     */
    SyEmp getEmpByEmpname(@Param("empNo") String empNo);

    /*
     *根据用户查询角色
     */
    Set<String> listRoles(@Param("empNo")String empNo);

    /**
     * 查询用户所有权限,返回值是权限名集合
     */
    Set<String> listPermissions(@Param("empNo")String empNo);

    /**
     * 一级目录
     */
    List<SyMemus> getOneMenu();

    /**
     * 二级目录
     */
    List<SyMemus> getTowMenu(@Param("id") int id);

    /**
     * 三级目录
     */
    List<SyMemus> getThreeMenu(@Param("id") int id);

}