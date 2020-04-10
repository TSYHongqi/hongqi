/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SqlProvider
 * Author:   TSYH
 * Date:     2020-02-06 14:48
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.util;



import com.hongqi.springboot.dao.dispatch.ManuScheduDao;
import com.hongqi.springboot.model.AccBusinessAdmissibility;
import com.hongqi.springboot.service.dispatch.ManuScheduService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-06
 * @since 1.0.0
 */

public class SqlProvider {

    /**
     * 查询宣传任务
     * @param para
     * @return
     */
    public String queryPropagandatask(Map<String, Object> para){
        StringBuffer sb = new StringBuffer();
       sb.append("select dp.*,se.empName,su.name from" +
               " dis_propagandatask dp INNER JOIN sy_emp se on dp.OperatorID=se.id INNER JOIN sy_units su on dp.OperationUnitID = su.id" +
               " where 1=1");
       if(para.get("outline")!=""&&para.get("releaseTime")!=null){
           sb.append(" and dp.outline like '%"+para.get("outline")+"%'");
       }else if(para.get("releaseTime")!=""&&para.get("releaseTime")!=null){
           sb.append(" and dp.releaseTime like '%"+para.get("releaseTime")+"%'");
       }else if(para.get("failureTime")!=""&&para.get("failureTime")!=null){
           sb.append(" and dp.failureTime like '%"+para.get("failureTime")+"%'");
       }else if(para.get("status")!=""&&para.get("status")!=null){
           sb.append(" and dp.status = "+para.get("status"));
       }
       sb.append(" order by dp.id desc");
       return sb.toString();
    }

    /**
     * 查询所有签收录入信息
     */
    public String querySignInput(Map<String, Object> para){
        StringBuffer sb = new StringBuffer();
        sb.append("select dw.*,se.empName,su.name from dis_workordersign dw INNER JOIN sy_emp se on dw.InputPersonID = se.id " +
                 "INNER JOIN sy_units su on dw.InputID =  su.id where dw.status=1 ");
        if(para.get("empName")!=""&&para.get("empName")!=null){
            sb.append(" and se.empName like '%"+para.get("empName")+"%'");
        }else if(para.get("workSheetNo")!=""&&para.get("workSheetNo")!=null){
            sb.append(" and dw.workSheetNo like '%"+para.get("workSheetNo")+"%'");
        }else if(para.get("courierint")!=""&&para.get("courierint")!=null){
            sb.append(" and dw.courierint = "+para.get("courierint"));
        }else if(para.get("name")!=""&&para.get("name")!=null){
            sb.append(" and su.name like '%"+para.get("name")+"%'");
        }else if(para.get("invalidateMark")!=""&&para.get("invalidateMark")!=null){
            sb.append(" and dw.invalidateMark = "+para.get("invalidateMark"));
        }else if(para.get("signType")!=""&&para.get("signType")!=null){
            sb.append(" and dw.signType = "+para.get("signType"));
        }else if(para.get("start")!=""&&para.get("start")!=null){
            sb.append(" and dw.signTime >= '"+para.get("start")+"'");
        }else if(para.get("end")!=""&&para.get("end")!=null){
            sb.append(" and dw.signTime <= '"+para.get("end")+"'");
        }
        sb.append(" order by dw.id desc");
        return sb.toString();
    }

    /**
     * 查询所有取消签收
     */
    public String queryDisCancelSign(Map<String, Object> para){
        StringBuffer sb = new StringBuffer();
        sb.append("select * from dis_cancelSign where ConStatus=1 or ConStatus=2");
        if(para.get("appNo")!=""&&para.get("appNo")!=null){
            sb.append(" and appNo like '%"+para.get("appNo")+"%'");
        }else if(para.get("workSheetNo")!=""&&para.get("workSheetNo")!=null){
            sb.append(" and workSheetNo like '%"+para.get("workSheetNo")+"%'");
        }else if(para.get("status")!=""&&para.get("status")!=null){
            sb.append(" and status = "+para.get("status"));
        }else if(para.get("why")!=""&&para.get("why")!=null){
            sb.append(" and why like '%"+para.get("why")+"%'");
        }else if(para.get("appPeople")!=""&&para.get("appPeople")!=null){
            sb.append(" and appPeople like '%"+para.get("appPeople")+"%'");
        }else if(para.get("appTime")!=""&&para.get("appTime")!=null){
            sb.append(" and appTime like '%"+para.get("appTime")+"%'");
        }else if(para.get("appUnit")!=""&&para.get("appUnit")!=null){
            sb.append(" and appUnit like '%"+para.get("appUnit")+"%'");
        }
        sb.append(" order by id desc");
        return sb.toString();
    }

    /**
     * 查询人工调度信息
     */
    public String queryManuSchedu(Map<String, Object> para){

        StringBuffer sb = new StringBuffer();
        sb.append("select ab.* from acc_businessadmissibility ab" +
                  " where ab.SingleType ='人工受理'");

        sb.append(" and ab.customNoModifyFlag != 0 ");

        if(para.get("businessNoticeNo")!=""&&para.get("businessNoticeNo")!=null){
            sb.append(" and ab.businessNoticeNo like '%"+para.get("businessNoticeNo")+"%'");
        }else if(para.get("reservationTime")!=""&&para.get("reservationTime")!=null){
            sb.append(" and ab.reservationTime like '%"+para.get("reservationTime")+"%'");
        }
        sb.append(" order by ab.id desc");
        return sb.toString();
    }
















}