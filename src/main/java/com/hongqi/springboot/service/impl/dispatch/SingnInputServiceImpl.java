/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SingnInputService
 * Author:   TSYH
 * Date:     2020-02-07 14:49
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
import com.hongqi.springboot.dao.dispatch.SignInputDao;
import com.hongqi.springboot.model.*;
import com.hongqi.springboot.service.dispatch.SingnInputService;
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
 * @create 2020-02-07
 * @since 1.0.0
 */
@Service
public class SingnInputServiceImpl implements SingnInputService {

    @Autowired
    private SignInputDao signInputDao;

    @Autowired
    private BasDeliveryStandardDao basDeliveryStandardDao;


    /**
     * 查询所有签收单
     */
    @Override
    public String findAllSingnInput(String page,String limit,String empName,String workSheetNo,String start,String end,
                                    String signType,String courierint,String name,String invalidateMark) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<DisWorkordersign> all = signInputDao.findAll(empName, workSheetNo, start, end, signType, courierint, name, invalidateMark);
        PageInfo<DisWorkordersign> pageInfo = new PageInfo<>(all);
        String jsonString = JSON.toJSONString(all);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }

    /**
     * 查询所有取消签收单
     * @param page
     * @param limit
     * @param appNo
     * @param workSheetNo
     * @param conStatus
     * @param why
     * @param appPeople
     * @param appTime
     * @param appUnit
     * @return
     */
    @Override
    public String queryDisCancelSign(String page, String limit, String appNo, String workSheetNo, String conStatus, String why, String appPeople, String appTime, String appUnit) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<DisCancelSign> allDis = signInputDao.findAllDis(appNo, workSheetNo, conStatus, why, appPeople, appTime, appUnit);
        PageInfo<DisCancelSign> pageInfo = new PageInfo<>(allDis);
        String jsonString = JSON.toJSONString(allDis);
        String jso = "{\"code\":0,\"msg\": \"\",\"count\":" + pageInfo.getTotal() + ",\"data\":" + jsonString + "}";
        return jso;
    }


    /**
     * 查询最新的工作单
     * @return
     */
    @Override
    public String queryMaxAppNo() {
        return signInputDao.queryMaxAppNo();
    }

    /**
     * 新增取消签收单
     * @param disCancelSign
     * @param session
     */
    @Override
    public void addDisCancelSign(DisCancelSign disCancelSign, HttpSession session) {
        DisWorkordersign disWorkordersign = signInputDao.queryAllDis(disCancelSign.getWorkSheetNo());
        //签收时间
        disCancelSign.setSignTime(disWorkordersign.getSignTime());
        //签收类型
        Integer signType = disWorkordersign.getSignType();
        if(signType==1){
            disCancelSign.setSignType("正常签收");
        }else{
            disCancelSign.setSignType("反向签收");
        }
        //签收单位
        disCancelSign.setSignUnit( disWorkordersign.getName());
        SyEmp emp = (SyEmp)session.getAttribute("emp");
        List<BasDeliveryStandard> basDeliveryStandards = basDeliveryStandardDao.addBind(emp.getEmpNo());
        //申请单位
        disCancelSign.setAppUnit(basDeliveryStandards.get(0).getUname());
        //申请人
        disCancelSign.setAppPeople(emp.getEmpName());
        //申请单号
        String s = signInputDao.queryMaxAppNo();
        if(s!=null) {
            String sqd = s.substring(3);
            int i = Integer.parseInt(sqd) + 1;
            disCancelSign.setAppNo("SQD" + i);
        }else{
            disCancelSign.setAppNo("SQD10001");
        }
        disCancelSign.setStatus(1);
        //新增
        signInputDao.addDisCancelSign(disCancelSign);

    }

    /**
     * 验证 start
     * @param worksheetNo
     * @return
     */
    @Override
    public DisWorkordersign queryAllDis(String worksheetNo) {
        return signInputDao.queryAllDis(worksheetNo);
    }

    @Override
    public DisCancelSign queryIf(String worksheetNo) {
        return signInputDao.queryIf(worksheetNo);
    }

    @Override
    public AccWorkSheet queryIfaccWor(String worksheetNo) {
        return signInputDao.queryIfaccWor(worksheetNo);
    }
    /**
     * end
     */

    /**
     * 同意
     * @param id
     */
    @Override
    public void confirm(Integer id,HttpSession session) {
        SyEmp emp = (SyEmp)session.getAttribute("emp");
        String empName = emp.getEmpName();
        signInputDao.confirm(id,empName); //同意
        signInputDao.updDisWorkordersign(id);//修改为已取消签收
    }


    /**
     * 拒绝
     * @param id
     * @param why
     */
    @Override
    public void refuse(Integer id, String why) {
        signInputDao.refuse(id,why);
    }


    /**
     * 作废
     * @param id
     */
    @Override
    public void delDisCancelSign(Integer id) {
        signInputDao.delDisCancelSign(id);
    }

    @Override
    public SyEmp ifEmpNo(String empNo) {
        return signInputDao.ifEmpNo(empNo);
    }

    /**
     * 新增签收单
     * @param workSheetNo
     * @param workOrderType
     * @param signType
     * @param signTime
     * @param recipient
     * @param courierint
     */
    @Override
    public void addDisSign(String workSheetNo, Integer workOrderType, Integer signType,
                           String signTime, String recipient, String courierint,HttpSession session) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DisWorkordersign disWorkordersign = new DisWorkordersign();
        SyEmp syEmp = signInputDao.ifEmpNo(courierint);
        //派收员工号
        disWorkordersign.setCourierint(courierint);
        //派收员工姓名
        disWorkordersign.setCourierName(syEmp.getEmpName());

        SyEmp syEmp1 = signInputDao.ifEmpNo(recipient);
        //签收人工号
        disWorkordersign.setRecipient(syEmp1.getEmpNo());
        //签收人姓名
        disWorkordersign.setRecipientName(syEmp1.getEmpName());
        //签收单位
        disWorkordersign.setSignUnit(syEmp1.getEmpUnit());
        //签收时间
        Date parse = sf.parse(signTime);
        disWorkordersign.setSignTime(parse);

        SyEmp emp = (SyEmp)session.getAttribute("emp");
        //录入人编码
        disWorkordersign.setInputPersonCode(emp.getEmpNo());
        //录入人id
        disWorkordersign.setInputPersonID(emp.getId());
        //录入单位id
        disWorkordersign.setInputID(emp.getEmpUnit());
        //工作单id
        AccWorkOrder workId = signInputDao.findWorkId(workSheetNo);
        disWorkordersign.setWorkOrderID(workId.getId());
        //工作单
        disWorkordersign.setWorkSheetNo(workSheetNo);
        //工作单类型
        disWorkordersign.setWorkOrderType(workOrderType);
        //签收类型
        disWorkordersign.setSignType(signType);
        //添加
        signInputDao.addSign(disWorkordersign);
        //修改
        signInputDao.updSign(workSheetNo);
    }



    /**
     * 查询签收人的单位
     */
    @Override
    public String queryUnits(String empNo) {
        SyEmp syEmp = signInputDao.queryUnits();
        SyEmp syEmp1 = signInputDao.ifEmpNo(empNo);
        if(syEmp1!=null) {
            if (syEmp.getEmpUnit() != syEmp1.getEmpUnit()) {
                return "a";
            }else{
                return "b";
            }
        }else{
            return "a";
        }
    }

    /**
     * 修改签收单
     * @param recipient
     * @param signTime
     * @param workSheetNo
     */
    @Override
    public void updateSign(String recipient, String signTime, String workSheetNo) {
        SyEmp syEmp = signInputDao.ifEmpNo(recipient);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            signInputDao.updateSign(recipient, sf.parse(signTime), workSheetNo,syEmp.getEmpName());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }




















}