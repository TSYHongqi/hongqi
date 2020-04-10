/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BasBasicArchivesServiceImpl
 * Author:   TSYH
 * Date:     2020-01-08 16:42
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.impl.basic;

import com.hongqi.springboot.dao.basic.BasBasicArchivesDao;
import com.hongqi.springboot.model.BasBasicArchiveSentry;
import com.hongqi.springboot.model.BasBasicArchives;
import com.hongqi.springboot.service.basic.BasBasicArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-08
 * @since 1.0.0
 */
@Service
public class BasBasicArchivesServiceImpl implements BasBasicArchivesService {

    @Autowired
    private BasBasicArchivesDao basBasicArchivesDao;

    @Override
    public List<BasBasicArchives> findAll(int id, String empName, String name, String operationTime) {
        return basBasicArchivesDao.findAll(id,empName,name,operationTime);
    }

    @Override
    public List<BasBasicArchiveSentry> findAllList(int id) {
        return basBasicArchivesDao.findAllList(id);
    }

    @Override
    public void addBasicArch(BasBasicArchives basBasicArchives) {
        basBasicArchivesDao.addBasicArch(basBasicArchives);
    }

    @Override
    public void updBasicArch(BasBasicArchives basBasicArchives) {
        basBasicArchivesDao.updBasicArch(basBasicArchives);
    }

    @Override
    public BasBasicArchives queryBasArchById(Integer id) {
        return basBasicArchivesDao.queryBasArchById(id);
    }

    @Override
    public void addBasicArchSentry(BasBasicArchiveSentry basBasicArchiveSentry) {
        basBasicArchivesDao.addBasicArchSentry(basBasicArchiveSentry);
    }

    @Override
    public void updBasicArchSentry(BasBasicArchiveSentry basBasicArchiveSentry) {
        basBasicArchivesDao.updBasicArchSentry(basBasicArchiveSentry);
    }

    @Override
    public BasBasicArchiveSentry queryBasArSenById(Integer id) {
        return basBasicArchivesDao.queryBasArSenById(id);
    }

    @Override
    public void delBasArSenById(Integer id) {
        basBasicArchivesDao.delBasArSenById(id);
    }
}