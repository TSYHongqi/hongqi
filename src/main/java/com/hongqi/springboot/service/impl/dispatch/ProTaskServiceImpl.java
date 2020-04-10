/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ProTaskServiceImpl
 * Author:   TSYH
 * Date:     2020-02-06 13:37
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.impl.dispatch;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongqi.springboot.dao.basic.BasDeliveryStandardDao;
import com.hongqi.springboot.dao.dispatch.ProTaskDao;
import com.hongqi.springboot.model.AccWorkOrder;
import com.hongqi.springboot.model.BasDeliveryStandard;
import com.hongqi.springboot.model.DisPropagandatask;
import com.hongqi.springboot.model.SyEmp;
import com.hongqi.springboot.service.basic.BasDeliveryStandardService;
import com.hongqi.springboot.service.dispatch.ProTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-06
 * @since 1.0.0
 */
@Service
public class ProTaskServiceImpl implements ProTaskService {

    @Autowired
    private ProTaskDao proTaskDao;

    @Autowired
    private BasDeliveryStandardDao basDeliveryStandardDao;
    /**
     * 查询宣传任务
     * @return
     */
    @Override
    public String findDisProTask(String page,String limit,String outline,String releaseTime,
                                 String failureTime,String status) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<DisPropagandatask> disPropagandatasks = proTaskDao.queryAll(outline, releaseTime, failureTime, status);
        PageInfo<DisPropagandatask> pageInfo = new PageInfo<>(disPropagandatasks);
        String jsonString = JSON.toJSONString(disPropagandatasks);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }

    /**
     * 新增宣传任务
     * @param disPropagandatask
     * @param session
     */

    @Override
    public void addDisProTask(DisPropagandatask disPropagandatask, HttpSession session) {
        SyEmp emp = (SyEmp) session.getAttribute("emp");
        //操作人员
        Integer id = emp.getId();
        //操作单位
        List<BasDeliveryStandard> bas = basDeliveryStandardDao.addBind(emp.getEmpNo());
        Integer id1 = bas.get(0).getOperationUnitID();
        disPropagandatask.setOperatorID(id);
        disPropagandatask.setOperationUnitID(id1);

        //新增
        proTaskDao.addDisProTask(disPropagandatask);
    }

    /**
     * 修改绑值
     * @param id
     * @return
     */
    @Override
    public String  findDisProTask(Integer id) {
        DisPropagandatask disPropagandatask = proTaskDao.queryDisProTaskById(id);
        String s = JSON.toJSONString(disPropagandatask);
        return s;
    }

    @Override
    public void updPropagandaTask(DisPropagandatask disPropagandatask) {
        //修改
        proTaskDao.updPropagandaTask(disPropagandatask);
    }


}