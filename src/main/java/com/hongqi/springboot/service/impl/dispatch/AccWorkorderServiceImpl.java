/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AccWorkorderDao
 * Author:   TSYH
 * Date:     2020-01-17 15:57
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.impl.dispatch;

import com.hongqi.springboot.dao.acceptance.BusinessAcceptanceDao;
import com.hongqi.springboot.dao.dispatch.AccWorkorderDao;
import com.hongqi.springboot.model.AccBusinessAdmissibility;
import com.hongqi.springboot.model.AccWorkOrder;
import com.hongqi.springboot.model.AccWorkSheet;
import com.hongqi.springboot.model.BasArea;
import com.hongqi.springboot.service.acceptance.BusinessAcceptanceService;
import com.hongqi.springboot.service.dispatch.AccWorkorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-17
 * @since 1.0.0
 */
@Service
public class AccWorkorderServiceImpl implements AccWorkorderService {

    @Autowired
    private AccWorkorderDao accWorkorderDao;


    @Override
    public List<AccWorkOrder> queryAllWorkOrder(String businessNoticeNo,String queryPwd,String phone,
                                                String workGenerationTime,String empNo,
                                                String shortMessageint) {
        return accWorkorderDao.queryAllWorkOrder(businessNoticeNo,queryPwd,phone,workGenerationTime,empNo,shortMessageint);
    }

    @Override
    public AccWorkOrder queryWorkById(Integer id) {
        return accWorkorderDao.queryWorkById(id);
    }

    @Override
    public void updateDistory(String businessNoticeNo) {
        accWorkorderDao.updateDistory(businessNoticeNo);
    }

    /**
     *查找工作单中是否存在业务员通知单
     * @param businessNoticeNo
     * @return
     */
    @Override
    public AccWorkSheet queryAcWoShIfBusNo(String businessNoticeNo,String workSheetNo) {
        return accWorkorderDao.queryAcWoShIfBusNo(businessNoticeNo,workSheetNo);
    }

    /**
     * 增加工作单
     * @param accWorkSheet
     */
    @Override
    public void addAccWorksheet(AccWorkSheet accWorkSheet) {
        accWorkorderDao.addAccWorksheet(accWorkSheet);
    }

    /**
     *应该在AccWorkorder中存在
     * @param businessNoticeNo
     * @return
     */
    @Override
    public AccBusinessAdmissibility queryAcWoNoIfBusNo(String businessNoticeNo) {
        return accWorkorderDao.queryAcWoNoIfBusNo(businessNoticeNo);
    }

    @Override
    public void updAccBusiness(AccBusinessAdmissibility accBusinessAdmissibility) {
        accWorkorderDao.updAccBusiness(accBusinessAdmissibility);
    }



    @Override
    public List<AccWorkSheet> queryAcc(String workGenerationTime,String workSheetNo,String packagesNum, String empNo,String name,String disabled) {
        return accWorkorderDao.queryAcc(workGenerationTime,workSheetNo,packagesNum,empNo,name,disabled);
    }

    @Override
    public AccWorkSheet queryAccWorShById(Integer id) {
        return accWorkorderDao.queryAccWorShById(id);
    }

    @Override
    public void worksheetQueryUpd(String productTime, String stowageRequirements, Integer id) {
        accWorkorderDao.worksheetQueryUpd(productTime,stowageRequirements,id);
    }

    @Override
    public void updWorksheetQuery(Integer id) {
        accWorkorderDao.updWorksheetQuery(id);
    }




}