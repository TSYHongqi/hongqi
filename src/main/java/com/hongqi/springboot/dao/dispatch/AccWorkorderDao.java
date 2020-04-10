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
package com.hongqi.springboot.dao.dispatch;

import com.hongqi.springboot.model.AccBusinessAdmissibility;
import com.hongqi.springboot.model.AccWorkOrder;
import com.hongqi.springboot.model.AccWorkSheet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-17
 * @since 1.0.0
 */
@Repository
public interface AccWorkorderDao {

    List<AccWorkOrder> queryAllWorkOrder(@Param("businessNoticeNo") String businessNoticeNo,
                                         @Param("queryPwd") String queryPwd,@Param("phone") String phone,
                                         @Param("workGenerationTime") String workGenerationTime,@Param("empNo") String empNo,
                                         @Param("shortMessageint") String shortMessageint);

    AccWorkOrder queryWorkById(@Param("id") Integer id);

    void updateDistory(@Param("businessNoticeNo") String id);

    /**
     *
     * 查找工作单中是否存在业务员通知单(BusinessNoticeNo)
     *
     * @param businessNoticeNo
     * @return
     */
    AccWorkSheet queryAcWoShIfBusNo(@Param("businessNoticeNo") String businessNoticeNo,@Param("workSheetNo"
    )String workSheetNo);


    /**
     * 增加工作单
     */
    void addAccWorksheet(AccWorkSheet accWorkSheet);


    AccBusinessAdmissibility queryAcWoNoIfBusNo(@Param("businessNoticeNo") String businessNoticeNo);


    void updAccBusiness(AccBusinessAdmissibility accBusinessAdmissibility);


    /**
     * 查找工作单
     * @return
     */
    List<AccWorkSheet> queryAcc(@Param("workGenerationTime") String workGenerationTime,
                                @Param("workSheetNo") String workSheetNo,@Param("packagesNum") String packagesNum,
                                @Param("empNo") String empNo,@Param("name") String name,@Param("disabled")String disabled);


    /**
     * 根据id查询工作单
     */
    AccWorkSheet queryAccWorShById(@Param("id") Integer id);


    /**
     * 修改工作单
     */
   void worksheetQueryUpd(@Param("productTime") String productTime,
                          @Param("stowageRequirements") String stowageRequirements,
                          @Param("id") Integer id);


    /**
     * 作废
     * @param id
     */
   void updWorksheetQuery(@Param("id") Integer id);

}