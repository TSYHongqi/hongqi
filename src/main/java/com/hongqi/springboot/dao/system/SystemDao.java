/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SystemDao
 * Author:   TSYH
 * Date:     2020-01-19 10:25
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.dao.system;

import com.hongqi.springboot.model.SyEmp;
import com.hongqi.springboot.model.SyMemus;
import com.hongqi.springboot.model.SyRole;
import com.hongqi.springboot.model.SyUnits;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-19
 * @since 1.0.0
 */
@Repository
public interface SystemDao {

    /**
     * 单位管理
     * @return
     */
    List<SyUnits> queryUnit(@Param("name") String name);

    /**
     * 新增
     */
    void addSysUnit(@Param("name") String name,@Param("remarks") String remarks,@Param("operatorID") Integer operatorID);


    /**
     * 修改
     */
    void updUnit(@Param("name") String name,@Param("remarks") String remarks,@Param("id") Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    SyUnits queryById(@Param("id") Integer id);

    /**
     * 删除
     */
    void delUnit(@Param("id") Integer id);

    /**
     * 查询所有员工
     */
    List<SyEmp> findAllEmp(@Param("disabled") Integer disabled,@Param("empName") String empName);


    /**
     * 查询所有栏目
     * @param text
     * @param parentID
     * @return
     */
    List<SyMemus> findAllMenu(@Param("text") String text,@Param("parentID") Integer parentID);


    /**
     * 上级栏目
     */
    List<SyMemus> findPre();

    /**
     * 上级栏目2
     */
    List<SyMemus> findPre2();
    /**
     * 查询所有角色
     */
    List<SyRole> findSysRole(@Param("disabled") Integer disabled,@Param("roleName") String roleName);

    /**
     * 查询最大的工号
     */
    int queryMaxEmpNo();

    /**
     * 新增员工信息
     */
    void addEmp(SyEmp syEmp);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    SyEmp findEmpById(@Param("id") Integer id);


    /**
     * 修改员工
     */
    void updEmp(SyEmp syEmp);


    /**
     * 删除
     */
    void delEmpById(@Param("id") Integer id);


    /**
     * 新增菜单
     */
    void addMenu(SyMemus syMenus);


    /**
     * 修改菜单
     */
    void updMenu(SyMemus syMemus);


    /**
     * 根据id查询菜单
     */
    SyMemus querySysMenuById(@Param("id") Integer id);

    /**
     * 查询没有下级目录的菜单
     * @return
     */
    List<SyMemus> queryLastMenu();

    /**
     *新增角色
     */
    void addRole(SyRole syRole);

    /**
     * 根据id查询角色
     */
    SyRole findRoleById(Integer id);

    /**
     * 修改用户
     */
    void updRole(SyRole syRole);


    /**
     * 修改栏目（删除）
     */
    void delRole(Integer id);


    /**
     * 修改角色(删除)
     */
    void delMenu(Integer id);

    /**
     * 该栏目是否能删除
     */
    SyMemus findMenuIfDel(@Param("id") Integer id);


    /**
     * 根据父id查询
     */
    List<SyMemus> queryMenuByP(@Param("parentID") Integer parentID);

    /**
     * 该角色所拥有的权限
     */
    List<SyMemus> queryPerByRId(@Param("id") Integer id);


   //根据mid菜单id删除
   void delSyRMByMid(@Param("menuID") Integer menuID,@Param("roleID") Integer roleID);

   //往sy_rolesmenus新添
    void addSyRM(@Param("roleID") Integer roleID,@Param("menuID") Integer menuID);

























}