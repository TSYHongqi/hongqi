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
package com.hongqi.springboot.service.dispatch;

import com.hongqi.springboot.model.AccBusinessAdmissibility;
import com.hongqi.springboot.model.AccWorkOrder;
import com.hongqi.springboot.model.AccWorkSheet;
import com.hongqi.springboot.model.BasArea;
import org.apache.ibatis.annotations.Param;
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
public interface AccWorkorderService {

    List<AccWorkOrder> queryAllWorkOrder(String businessNoticeNo,String queryPwd,String phone,
                                         String workGenerationTime,String empNo,
                                         String shortMessageint);

    /**
     * 通过id查询工单
     */
    AccWorkOrder queryWorkById(Integer id);

    /**
     * 销单
     * @param businessNoticeNo
     */
    void updateDistory(String businessNoticeNo);


    /**
     *查找工作单中是否存在业务员通知单(BusinessNoticeNo)
     */
    AccWorkSheet queryAcWoShIfBusNo(String businessNoticeNo,String workSheetNo);


    /**
     * 增加工作单
     */
    void addAccWorksheet(AccWorkSheet accWorkSheet);

    /**
     * 应该在AccWorkorder中存在
     * @param businessNoticeNo
     * @return
     */
    AccBusinessAdmissibility queryAcWoNoIfBusNo(String businessNoticeNo);

    void updAccBusiness(AccBusinessAdmissibility accBusinessAdmissibility);
    /**
     * 查找工作单
     * @return
     */
    List<AccWorkSheet> queryAcc(String workGenerationTime,String workSheetNo,String packagesNum, String empNo,
                                String name,String disabled);


    /**
     * 根据id查询工作单
     */
    AccWorkSheet queryAccWorShById(Integer id);
    /**
     * 修改工作单
     */
    void worksheetQueryUpd(String productTime,String stowageRequirements,Integer id);
    /**
     * 作废
     * @param id
     */
    void updWorksheetQuery(Integer id);





}