/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BusinessAcceptanceDao
 * Author:   TSYH
 * Date:     2020-01-09 16:35
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.dao.acceptance;

import com.hongqi.springboot.controller.acceptance.BusinessAcceptanceController;
import com.hongqi.springboot.model.AccBusinessAdmissibility;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-09
 * @since 1.0.0
 */
@Repository
public interface BusinessAcceptanceDao {

   /**
    * 查询
    * @return
    */
   List<AccBusinessAdmissibility> findBusinessAcceptance(@Param("businessNoticeNo") String businessNoticeNo,
                                                         @Param("customCode") String customCode);
}