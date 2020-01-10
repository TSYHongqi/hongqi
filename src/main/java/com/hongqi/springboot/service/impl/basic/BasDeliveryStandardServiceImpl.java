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
package com.hongqi.springboot.service.impl.basic;

import com.hongqi.springboot.dao.basic.BasDeliveryStandardDao;
import com.hongqi.springboot.model.BasDeliveryStandard;
import com.hongqi.springboot.service.basic.BasDeliveryStandardService;
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

    public List<BasDeliveryStandard> findStandards(BasDeliveryStandard basDeliveryStandard) {
        return basDeliveryStandardDao.findStandards(basDeliveryStandard);
    }

    @Override
    public void updateInvalidateSign(List ids) {
       basDeliveryStandardDao.updateInvalidateSign(ids);
    }

    @Override
    public BasDeliveryStandard queryById(Integer id) {
        return basDeliveryStandardDao.queryById(id);
    }

    @Override
    public void updateStandards(BasDeliveryStandard basDeliveryStandard) {
        basDeliveryStandardDao.updateStandards(basDeliveryStandard);
    }

    @Override
    public void addStandards(BasDeliveryStandard basDeliveryStandard) {
        basDeliveryStandardDao.addStandards(basDeliveryStandard);
    }

    @Override
    public List<BasDeliveryStandard> addBind(String empNo) {
        return basDeliveryStandardDao.addBind(empNo);
    }
}