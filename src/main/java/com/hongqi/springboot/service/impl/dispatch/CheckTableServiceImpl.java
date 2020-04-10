/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CheckTableServiceImpl
 * Author:   TSYH
 * Date:     2020-02-04 9:29
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.impl.dispatch;

import com.hongqi.springboot.dao.acceptance.BusinessAcceptanceDao;
import com.hongqi.springboot.dao.dispatch.CheckTableDao;
import com.hongqi.springboot.dao.dispatch.ManuScheduDao;
import com.hongqi.springboot.model.*;
import com.hongqi.springboot.service.dispatch.CheckTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-04
 * @since 1.0.0
 */
@Service
public class CheckTableServiceImpl implements CheckTableService {

    @Autowired
    private CheckTableDao checkTableDao;

    @Autowired
    private BusinessAcceptanceDao businessAcceptanceDao;

    @Autowired
    private ManuScheduDao manuScheduDao;


    @Override
    public BasZoneInfo queryZone(String zoneCode) {
        return checkTableDao.queryZone(zoneCode);
    }

    @Override
    public SyEmp queryEmp(String empNo) {
        return checkTableDao.queryEmp(empNo);
    }

    @Override
    public SyUnits queryUnits(String name) {
        return checkTableDao.queryUnits(name);
    }

    @Override
    public BasArea ifExitsAddr(String county) {
        return businessAcceptanceDao.ifExitsAddr(county);
    }

    /**
     * 工单号
     */
    public String getJobNo(){
        String jobNo = ""; //工单号
        AccWorkOrder accWorkOrder = businessAcceptanceDao.queryMax();
        if (accWorkOrder != null) {//工单增加，业务通知单不变
            String substring = accWorkOrder.getJobNo().substring(2);
            int i = Integer.parseInt(substring) + 1;
            jobNo = "GD" + i;
        } else {
            jobNo = "GD" + 10001;
        }
        return jobNo;
    }

    //最新调度信息
    public String getDis(){
        String dis = checkTableDao.findDispatchHistory();
        String dispatchSequence = "";//调度序号
        if(dis!=null){
            String substring = dis.substring(2);
            int i = Integer.parseInt(substring) + 1;
            return dispatchSequence = "DX"+i;
        }else{
            return dispatchSequence = "DX"+10001;
        }
    }

    /**
     * 短信序号
     */
    public Integer getShortMessage(Integer id) {
        int shortMessageint = 0;//短信序号
        //根据取货人员信息得到短信序号
        Integer accWorkOrder1 = businessAcceptanceDao.queryShortMessageint(id);
        if (accWorkOrder1 != null) {
            return shortMessageint = accWorkOrder1 + 1;
        } else {
            return shortMessageint = 10001;
        }
    }

    /**
     * 根据定区编码改单
     * @param id
     * @param method
     * @param target
     * @param pickupAddress
     * @return
     */
    public String updSinZonecode(Integer id, String method, String target, String pickupAddress) {
        BasZoneInfo bp = checkTableDao.queryZone(target);
        BasAssociatedAddress basAssoAddr = checkTableDao.findBasAssoAddr(pickupAddress);
        BasAssociatedAddress basAssociatedAddress = new BasAssociatedAddress();
        basAssociatedAddress.setCity(bp.getCity());
        basAssociatedAddress.setAssociatedPartitions(bp.getId());
        basAssociatedAddress.setZoneCode(target);
        basAssociatedAddress.setCustomAddress(pickupAddress);

        BasAssociateMember basAssociateMember = new BasAssociateMember();
        basAssociateMember.setEmpName(bp.getEmpName());
        basAssociateMember.setEmpNo(bp.getEmpNo());
        basAssociateMember.setZoneCode(bp.getZoneCode());
        basAssociateMember.setSubordinateUnit(bp.getEmpUnit());
        //建立该地址与目标定区的关系
        if (basAssoAddr == null) {
            checkTableDao.addBasAssociated(basAssociatedAddress);
            checkTableDao.addBasAssociMember(basAssociateMember);
        }
        //根据id查询工单信息
        AllAccBusAdmiWorkOr allAccBusAdmiWorkOr = checkTableDao.queryAworkById(id);
        //销毁工单
        checkTableDao.destoryAccwork(id);
        /**
         * 获得最新的业务通知单号码
         */
        String jobNo = getJobNo();
        //短信序号
        Integer shortMessageint = getShortMessage(bp.getEid());
        allAccBusAdmiWorkOr.setJobNo(jobNo);
        allAccBusAdmiWorkOr.setShortMessageint(shortMessageint);
        allAccBusAdmiWorkOr.setJobType(1);
        allAccBusAdmiWorkOr.setAfterSingleNum(0);
        allAccBusAdmiWorkOr.setSmallMemberNum(bp.getEid());
        allAccBusAdmiWorkOr.setPickupUnit(bp.getEmpUnit());
        //增加新工单
        checkTableDao.addWorkOrder(allAccBusAdmiWorkOr);
        /**
         * 修改受理表
         */
        checkTableDao.updAccBusById(bp.getEmpName(), bp.getPhone(), bp.getEid(), bp.getEmpUnit(), bp.getCity(), allAccBusAdmiWorkOr.getAid());
        return allAccBusAdmiWorkOr.getShortMessageint() + "【新】" + allAccBusAdmiWorkOr.getBusinessNoticeNo() + " " + allAccBusAdmiWorkOr.getCustomName() + " " + allAccBusAdmiWorkOr.getPickupAddress() + " " +
                allAccBusAdmiWorkOr.getTelPhone() + " " + allAccBusAdmiWorkOr.getWeight() + " " + allAccBusAdmiWorkOr.getVolume() + " "
                + allAccBusAdmiWorkOr.getSendAddress() + " " + allAccBusAdmiWorkOr.getImportantHints();
    }

    /**
     * 小件员工号 start
     */
    @Override
    public String updSinSamllPeople(Integer id, String method, String target) {
        SyEmp syEmp = checkTableDao.queryEmp(target);
        //根据id查询工单信息
        AllAccBusAdmiWorkOr allAccBusAdmiWorkOr = checkTableDao.queryAworkById(id);
        //销毁工单
        checkTableDao.destoryAccwork(id);
        /**
         * 获得最新的业务通知单号码
         */
        allAccBusAdmiWorkOr.setShortMessageint(getShortMessage(syEmp.getId()));

        String jobNo = getJobNo();
        allAccBusAdmiWorkOr.setJobNo(jobNo); //工单增加，业务通知单不变
        allAccBusAdmiWorkOr.setJobType(1);
        allAccBusAdmiWorkOr.setAfterSingleNum(0);
        allAccBusAdmiWorkOr.setSmallMemberNum(syEmp.getId());
        allAccBusAdmiWorkOr.setPickupUnit(syEmp.getEmpUnit());

        //增加新工单
        checkTableDao.addWorkOrder(allAccBusAdmiWorkOr);

        /**
         * 修改受理表
         */
        checkTableDao.updAccBusById(syEmp.getEmpName(), syEmp.getPhone(), syEmp.getId(), syEmp.getEmpUnit(), allAccBusAdmiWorkOr.getCity(), allAccBusAdmiWorkOr.getAid());


        return allAccBusAdmiWorkOr.getShortMessageint() + "【新】" + allAccBusAdmiWorkOr.getBusinessNoticeNo() + " " + allAccBusAdmiWorkOr.getCustomName() + " " + allAccBusAdmiWorkOr.getPickupAddress() + " " +
                allAccBusAdmiWorkOr.getTelPhone() + " " + allAccBusAdmiWorkOr.getWeight() + " " + allAccBusAdmiWorkOr.getVolume() + " "
                + allAccBusAdmiWorkOr.getSendAddress() + " " + allAccBusAdmiWorkOr.getImportantHints();

    }



    /**
     * 单位
     * @param id
     * @param method
     * @param target
     * @return
     */
    @Override
    public String updSinUnits(Integer id, String method, String target,HttpSession session) {
        //根据id查询工单信息
        AllAccBusAdmiWorkOr allAccBusAdmiWorkOr = checkTableDao.queryAworkById(id);
        int pickupUnit = allAccBusAdmiWorkOr.getPickupUnit();
        SyUnits units = checkTableDao.queryUnits(target);
        SyEmp syEmp = checkTableDao.queryUnitByName(target);
        //查询要转变的单位是否与原单位一致

        //销毁工单
        checkTableDao.destoryAccwork(id);
        /**
         * 获得最新的业务通知单号码
         */
        allAccBusAdmiWorkOr.setShortMessageint(getShortMessage(syEmp.getId()));
        String jobNo = getJobNo();
        allAccBusAdmiWorkOr.setJobNo(jobNo); //工单增加，业务通知单不变
        allAccBusAdmiWorkOr.setJobType(1);
        allAccBusAdmiWorkOr.setAfterSingleNum(0);
        allAccBusAdmiWorkOr.setSmallMemberNum(syEmp.getId());
        allAccBusAdmiWorkOr.setPickupUnit(syEmp.getEmpUnit());

        //增加新工单
        checkTableDao.addWorkOrder(allAccBusAdmiWorkOr);
        //最新调度历史
        String dis = getDis();
        //赋值
        SyEmp emp = (SyEmp)session.getAttribute("emp");
        DisDispatchhistory dispatchHistory = new DisDispatchhistory();
        dispatchHistory.setDispatchSequence(dis);
        dispatchHistory.setTransferredUnit(syEmp.getEmpUnit());
        dispatchHistory.setOperatorID(emp.getId());
        dispatchHistory.setOperationUnitID(emp.getEmpUnit());
        dispatchHistory.setStatus(1);
        dispatchHistory.setBusinessNoticeNo(allAccBusAdmiWorkOr.getBusinessNoticeNo());
        /**
         * 修改受理表
         */
        checkTableDao.updAccBusById(syEmp.getEmpName(), syEmp.getPhone(), syEmp.getId(), syEmp.getEmpUnit(), allAccBusAdmiWorkOr.getCity(), allAccBusAdmiWorkOr.getAid());
        if(units.getId()!= pickupUnit){
            //修改调度历史表
            manuScheduDao.updDisHistory(allAccBusAdmiWorkOr.getBusinessNoticeNo());
            //不一致，生成调度历史信息
            checkTableDao.addDispatchHistory(dispatchHistory);
        }
        return  allAccBusAdmiWorkOr.getShortMessageint() + "【新】" + allAccBusAdmiWorkOr.getBusinessNoticeNo() + " " + allAccBusAdmiWorkOr.getCustomName() + " " + allAccBusAdmiWorkOr.getPickupAddress() + " " +
                allAccBusAdmiWorkOr.getTelPhone() + " " + allAccBusAdmiWorkOr.getWeight() + " " + allAccBusAdmiWorkOr.getVolume() + " "
                + allAccBusAdmiWorkOr.getSendAddress() + " " + allAccBusAdmiWorkOr.getImportantHints();

    }

    /**
     * 重发
     * @param id
     * @return
     */
    @Override
    public String reSend(Integer id) {
        AllAccBusAdmiWorkOr allAccBusAdmiWorkOr = checkTableDao.queryAworkById(id);
        return  allAccBusAdmiWorkOr.getShortMessageint() + "【新】" + allAccBusAdmiWorkOr.getBusinessNoticeNo() + " " + allAccBusAdmiWorkOr.getCustomName() + " " + allAccBusAdmiWorkOr.getPickupAddress() + " " +
                allAccBusAdmiWorkOr.getTelPhone() + " " + allAccBusAdmiWorkOr.getWeight() + " " + allAccBusAdmiWorkOr.getVolume() + " "
                + allAccBusAdmiWorkOr.getSendAddress() + " " + allAccBusAdmiWorkOr.getImportantHints();

    }


}