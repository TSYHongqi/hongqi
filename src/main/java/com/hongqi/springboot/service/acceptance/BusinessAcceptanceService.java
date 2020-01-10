/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BusinessAcceptanceService
 * Author:   TSYH
 * Date:     2020-01-09 16:52
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.acceptance;

import com.hongqi.springboot.model.AccBusinessAdmissibility;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-09
 * @since 1.0.0
 */
public interface BusinessAcceptanceService {

    /**
     * 查询
     * @return
     */
    List<AccBusinessAdmissibility> findBusinessAcceptance(String businessNoticeNo, String customCode);

}