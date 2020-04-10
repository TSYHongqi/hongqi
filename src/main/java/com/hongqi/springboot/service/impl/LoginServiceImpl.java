/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: LoginServiceIm
 * Author:   TSYH
 * Date:     2020-01-05 2:26
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.impl;

import com.hongqi.springboot.dao.LoginDao;
import com.hongqi.springboot.model.SyEmp;
import com.hongqi.springboot.model.SyMemus;
import com.hongqi.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    /**
     * 根据用户名查找用户
     * @param empName
     * @return
     */
    @Override
    public SyEmp getEmpByEmpname(String empName) {
        return loginDao.getEmpByEmpname(empName);
    }

    /**
     * 根据用户查询角色
     * @param empName
     * @return
     */
    @Override
    public Set<String> listRoles(String empName) {
        return loginDao.listRoles(empName);
    }

    /**
     * 查询用户所有权限,返回值是权限名集合
     * @param empName
     * @return
     */
    @Override
    public Set<String> listPermissions(String empName,Integer roleID) {
        return loginDao.listPermissions(empName,roleID);
    }

    @Override
    public List<SyMemus> getOneMenu(String empNo) {
        return loginDao.getOneMenu(empNo);
    }

    @Override
    public List<SyMemus> getTowMenu(int id,String empNo) {
        return loginDao.getTowMenu(id,empNo);
    }

    @Override
    public List<SyMemus> getThreeMenu(int id,String empNo) {
        return loginDao.getThreeMenu(id,empNo);
    }
}