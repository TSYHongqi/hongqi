/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BusinessAcceptanceServiceImpl
 * Author:   TSYH
 * Date:     2020-01-09 16:55
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.impl.acceptance;

import com.hongqi.springboot.controller.acceptance.BusinessAcceptanceController;
import com.hongqi.springboot.dao.acceptance.BusinessAcceptanceDao;
import com.hongqi.springboot.model.AccBusinessAdmissibility;
import com.hongqi.springboot.service.acceptance.BusinessAcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-09
 * @since 1.0.0
 */
@Service
public class BusinessAcceptanceServiceImpl implements BusinessAcceptanceService {

    @Autowired
    private BusinessAcceptanceDao businessAcceptanceDao;

    @Override
    public List<AccBusinessAdmissibility> findBusinessAcceptance(String businessNoticeNo, String customCode) {
        return businessAcceptanceDao.findBusinessAcceptance(businessNoticeNo,customCode);
    }
}