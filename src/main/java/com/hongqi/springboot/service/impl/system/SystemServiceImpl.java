/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SystemServiceImpl
 * Author:   TSYH
 * Date:     2020-01-19 10:28
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.impl.system;

import com.hongqi.springboot.dao.system.SystemDao;
import com.hongqi.springboot.model.SyEmp;
import com.hongqi.springboot.model.SyMemus;
import com.hongqi.springboot.model.SyRole;
import com.hongqi.springboot.model.SyUnits;
import com.hongqi.springboot.service.system.SystemService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-19
 * @since 1.0.0
 */
@Service
public class SystemServiceImpl implements SystemService {


    @Autowired
    private SystemDao systemDao;

    /**
     * 单位管理
     * @return
     */
    @Override
    public List<SyUnits> queryUnit(String name) {
        return systemDao.queryUnit(name);
    }

    @Override
    public void addSysUnit(String name, String remarks, Integer operatorID) {
        systemDao.addSysUnit(name,remarks,operatorID);
    }

    @Override
    public void updUnit(String name, String remarks,Integer id) {
        systemDao.updUnit(name,remarks,id);
    }


    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public SyUnits queryById(Integer id) {
        return systemDao.queryById(id);
    }

    @Override
    public void delUnit(Integer id) {
        systemDao.delUnit(id);
    }

    @Override
    public List<SyEmp> findAllEmp(Integer disabled, String empName) {
        return systemDao.findAllEmp(disabled,empName);
    }

    @Override
    public List<SyMemus> findAllMenu(String text, Integer parentID) {
        return systemDao.findAllMenu(text,parentID);
    }

    @Override
    public List<SyMemus> findPre() {
        return systemDao.findPre();
    }

    @Override
    public List<SyRole> findSysRole(Integer disabled,String roleName) {
        return systemDao.findSysRole(disabled,roleName);
    }

    @Override
    public int queryMaxEmpNo() {
        return systemDao.queryMaxEmpNo();
    }

    @Override
    public void addEmp(SyEmp syEmp) {
        systemDao.addEmp(syEmp);
    }

    @Override
    public SyEmp findEmpById(Integer id) {
        return systemDao.findEmpById(id);
    }

    @Override
    public void updEmp(SyEmp syEmp) {
        systemDao.updEmp(syEmp);
    }

    @Override
    public void delEmpById(Integer id) {
        systemDao.delEmpById(id);
    }

    @Override
    public void addMenu(SyMemus syMenus) {
        systemDao.addMenu(syMenus);
    }

    @Override
    public void updMenu(SyMemus syMemus) {
        systemDao.updMenu(syMemus);
    }

    @Override
    public SyMemus querySysMenuById(Integer id) {
        return systemDao.querySysMenuById(id);
    }

    @Override
    public List<SyMemus> queryLastMenu() {
        return systemDao.queryLastMenu();
    }

    @Override
    public List<SyMemus> findPre2() {
        return systemDao.findPre2();
    }

    @Override
    public void addRole(SyRole syRole) {
        systemDao.addRole(syRole);
    }

    @Override
    public SyRole findRoleById(Integer id) {
        return systemDao.findRoleById(id);
    }

    @Override
    public void updRole(SyRole syRole) {
        systemDao.updRole(syRole);
    }

    @Override
    public void delRole(Integer id) {
        systemDao.delRole(id);
    }

    @Override
    public void delMenu(Integer id) {
        systemDao.delMenu(id);
    }

    @Override
    public SyMemus findMenuIfDel(Integer id) {
        return systemDao.findMenuIfDel(id);
    }

    @Override
    public List<SyMemus> queryMenuByP(Integer parentID) {
        return systemDao.queryMenuByP(parentID);
    }

    @Override
    public List<SyMemus> queryPerByRId(Integer id) {
        return systemDao.queryPerByRId(id);
    }

    @Override
    public void delSyRMByMid(Integer menuID,Integer roleID) {
        systemDao.delSyRMByMid(menuID,roleID);
    }

    @Override
    public void addSyRM(Integer roleID, Integer menuID) {
        systemDao.addSyRM(roleID,menuID);
    }


}