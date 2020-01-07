/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BasDeliveryStandardServiceImpl
 * Author:   TSYH
 * Date:     2020-01-06 20:26
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.impl;

import com.hongqi.springboot.dao.BasDeliveryStandardDao;
import com.hongqi.springboot.model.BasDeliveryStandard;
import com.hongqi.springboot.service.BasDeliveryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-06
 * @since 1.0.0
 */
@Service
public class BasDeliveryStandardServiceImpl implements BasDeliveryStandardService {

    @Autowired
    private BasDeliveryStandardDao basDeliveryStandardDao;

    public List<BasDeliveryStandard> findStandards() {
        return basDeliveryStandardDao.findStandards();
    }
}