/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ManuScheduServiceImpl
 * Author:   TSYH
 * Date:     2020-02-10 18:06
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.impl.dispatch;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongqi.springboot.dao.acceptance.BusinessAcceptanceDao;
import com.hongqi.springboot.dao.dispatch.CheckTableDao;
import com.hongqi.springboot.dao.dispatch.ManuScheduDao;
import com.hongqi.springboot.model.*;
import com.hongqi.springboot.service.dispatch.ManuScheduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-10
 * @since 1.0.0
 */
@Service
public class ManuScheduServiceImpl implements ManuScheduService {

    @Autowired
    private ManuScheduDao manuScheduDao;

    @Autowired
    private CheckTableDao checkTableDao;

    @Autowired
    private BusinessAcceptanceDao businessAcceptanceDao;

    /**
     * 查询人工调度信息
     */
    @Override
    public String queryManuSchede(String page, String limit, String businessNoticeNo, String reservationTime) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<AccBusinessAdmissibility> all = manuScheduDao.findAll(businessNoticeNo, reservationTime);
        PageInfo<AccBusinessAdmissibility> pageInfo = new PageInfo<>(all);
        String jsonString = JSON.toJSONString(all);

        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";

        return jso;
    }

    /**
     * 查找所有单位
     */
    @Override
    public String queryAllUnits() {
        List<SyUnits> syUnits = manuScheduDao.queryAllUnits();
        String s = JSON.toJSONString(syUnits);

        return s;
    }

    /**
     * 查找单位下的员工号
     * @param empUnit
     * @return
     */
    @Override
    public String queryEmpbyUid(Integer empUnit) {
        List<SyEmp> syEmps = manuScheduDao.queryEmpbyUid(empUnit);
        String s = JSON.toJSONString(syEmps);

        return s;
    }

    /**
     * 分配
     * @param name
     * @param empNo
     */
    @Override
    public String distribution(String name, String empNo, Integer id, HttpSession session) {
        AccBusinessAdmissibility acc = businessAcceptanceDao.queryById(id);//查询该业务单信息
        //新增调度历史表
        SyEmp emp = (SyEmp)session.getAttribute("emp");
        String dis = checkTableDao.findDispatchHistory();
        String dispatchSequence = "";//调度序号
        if(dis!=null){
            String substring = dis.substring(2);
            int i = Integer.parseInt(substring) + 1;
            dispatchSequence = "DX"+i;
        }else{
            dispatchSequence = "DX"+10001;
        }
        DisDispatchhistory disDispatchhistory = new DisDispatchhistory();
        disDispatchhistory.setDispatchSequence(dispatchSequence);
        disDispatchhistory.setStatus(1);
        disDispatchhistory.setTransferredUnit(Integer.parseInt(name));
        disDispatchhistory.setOperatorID(emp.getId());
        disDispatchhistory.setOperationUnitID(emp.getEmpUnit());
        disDispatchhistory.setBusinessNoticeNo(acc.getBusinessNoticeNo());

        if(acc.getProcessingUnit()!= Integer.parseInt(name)){
            //修改调度历史单
            manuScheduDao.updDisHistory(acc.getBusinessNoticeNo());
            //不一致，生成调度历史信息
            checkTableDao.addDispatchHistory(disDispatchhistory);
        }
        //先根据empNo得到小件员信息
        SyEmp syEmp = manuScheduDao.queryEmpByNo(empNo);
        System.out.println("==============="+empNo);
        if(syEmp.getId()!=acc.getPickerInfo()){ //取件人员不一致
            //修改业务受理表信息
            manuScheduDao.updAccBuss(Integer.parseInt(name),id,syEmp.getPhone(),syEmp.getEmpName(),syEmp.getId());
            //修改
            manuScheduDao.updAwork(syEmp.getId(),syEmp.getEmpUnit(),acc.getBusinessNoticeNo());
            //销毁工单
            businessAcceptanceDao.destorywork(acc.getBusinessNoticeNo());
            //产生新工单
            String jobNo ="";
            int shortMessageint = 0;//短信序号
            AccWorkOrder accWorkOrder = businessAcceptanceDao.queryMax();
            //根据取货人员信息得到短信序号
            Integer accWorkOrder1 = businessAcceptanceDao.queryShortMessageint(syEmp.getId());
            if(accWorkOrder1 != null){
                shortMessageint= accWorkOrder1 + 1;
            }else{
                shortMessageint= 10001;
            }
            if(accWorkOrder!=null){
                String substring = accWorkOrder.getJobNo().substring(2);
                int i = Integer.parseInt(substring)+1;
                jobNo = "GD"+i;
            }else{
                jobNo = "GD"+ 10001;
            }
            businessAcceptanceDao.addWorkOrder1(acc.getBusinessNoticeNo(),jobNo,1,1,shortMessageint,syEmp.getId(),Integer.parseInt(name), acc.getReservationTime());

            return  shortMessageint + "【新】" + acc.getBusinessNoticeNo() + " " + acc.getCustomName() + " " + acc.getPickupAddress() + " " +
                    acc.getTelPhone() + " " + acc.getWeight() + " " + acc.getVolume() + " "
                    + acc.getSendAddress() + " " + acc.getImportantHints();
        }else{
            return "a";
        }

    }
    /**
     * 查找人工调度详情
     */
    @Override
    public String findAllDetailByBus(String businessNoticeNo) {
        AllAccBusAdmiWorkOr all = manuScheduDao.queryAllAccAdmiWorkOr(businessNoticeNo);
        String s = JSON.toJSONString(all);
        return s;
    }

    @Override
    public void findOneAccBusiness(HttpSession session, String businessNoticeNo) {

        AccBusinessAdmissibility oneAccBusiness = manuScheduDao.findOneAccBusiness(businessNoticeNo);

        session.setAttribute("oneAccBusiness",oneAccBusiness);

    }




}