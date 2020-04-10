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
package com.hongqi.springboot.service.dispatch;

import com.hongqi.springboot.model.AccWorkSheet;
import com.hongqi.springboot.model.DisCancelSign;
import com.hongqi.springboot.model.DisWorkordersign;
import com.hongqi.springboot.model.SyEmp;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-07
 * @since 1.0.0
 */
public interface SingnInputService {
    /**
     * 查询所有签收单
     */
    String findAllSingnInput(String page,String limit,String empName,String workSheetNo,String start,String end,
                             String signType,String courierint,String name,String invalidateMark);

    /**
     * 查询所有取消签收单
     */
    String queryDisCancelSign(String page,String limit,String appNo,String workSheetNo,String status,String why,
                              String appPeople, String appTime,String appUnit);

    /**
     * 查询最新的工作单
     * @return
     */
    String queryMaxAppNo();

    /**
     * 新增取消签收单
     */
    void addDisCancelSign(DisCancelSign disCancelSign, HttpSession session);


    /**
     * 验证该工作单
     */
    DisWorkordersign queryAllDis(String worksheetNo);

    DisCancelSign queryIf(String worksheetNo);

    AccWorkSheet queryIfaccWor(String worksheetNo);
    /**
     * 同意
     */
    void confirm(Integer id,HttpSession session);

    /**
     * 拒绝
     */
    void refuse(Integer id,String why);

    /**
     * 作废
     */
    void delDisCancelSign(Integer id);

    /**
     * 员工是否存在
     */
    SyEmp ifEmpNo(String empNo);

    /**
     * 新增签收单
     */
    void addDisSign(String workSheetNo,Integer workOrderType,Integer signType,String signTime,
                    String  recipient,String courierint,HttpSession session) throws Exception;
    /**
     * 查询签收人的单位
     */
   String queryUnits(String empNo);
    /**
     * 修改签收单
     */
    void updateSign(String recipient,String signTime,String workSheetNo);

















}