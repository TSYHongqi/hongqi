/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ManuScheduController
 * Author:   TSYH
 * Date:     2020-02-10 17:03
 * Description: 人工调度
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.dao.dispatch;

import com.hongqi.springboot.model.AccBusinessAdmissibility;
import com.hongqi.springboot.model.AllAccBusAdmiWorkOr;
import com.hongqi.springboot.model.SyEmp;
import com.hongqi.springboot.model.SyUnits;
import com.hongqi.springboot.util.SqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈人工调度〉
 *
 * @author TSYH
 * @create 2020-02-10
 * @since 1.0.0
 */
@Repository
public interface ManuScheduDao {


    /**
     * 查询人工调度信息
     */
    @SelectProvider(type = SqlProvider.class, method = "queryManuSchedu")
    List<AccBusinessAdmissibility> findAll(@Param("businessNoticeNo") String businessNoticeNo,
                                           @Param("reservationTime") String reservationTime);

    /**
     * 查找所有单位
     */
    @Select("select * from sy_units")
    List<SyUnits> queryAllUnits();

    /**
     * 查找员工号
     * @param empUnit
     * @return
     */
    @Select("select * from sy_emp where empUnit=#{empUnit}")
    List<SyEmp> queryEmpbyUid(@Param("empUnit") Integer empUnit);

    /**
     * 修改业务受理表
     */
    @Update("update acc_businessadmissibility set processingUnit=#{processingUnit} where id= #{id}")
    void updAccBussUnit(@Param("processingUnit") Integer processingUnit,@Param("id") Integer id);

    /**
     * 修改业务受理表
     */
    @Update("update acc_businessadmissibility set processingUnit=#{processingUnit}, phone=#{phone}," +
            "linkman=#{linkman},pickerInfo =#{pickerInfo} " +
            "where id=#{id}")
    void updAccBuss(@Param("processingUnit") Integer processingUnit,
                    @Param("id") Integer id,
                    @Param("phone") String phone,
                    @Param("linkman") String linkman,
                    @Param("pickerInfo") Integer pickerInfo);
    @Update("update acc_workorder set SmallMemberNum = #{smallMemberNum},pickupUnit=#{pickupUnit} " +
            " where businessNoticeNo=#{businessNoticeNo} and jobType!=3")
    void updAwork(@Param("smallMemberNum") Integer smallMemberNum ,@Param("pickupUnit") Integer pickupUnit,@Param("businessNoticeNo") String businessNoticeNo);

    /**
     * 查找人工调度详情
     */
    @Select("select sus.name as pname,aw.*,su.name,ss.name as uname,se.empName,dd.dispatchSequence,dd.status,dd.remark," +
            " dd.reason from acc_businessadmissibility ab INNER JOIN " +
            " acc_workorder aw on ab.BusinessNoticeNo=aw.BusinessNoticeNo " +
            " INNER JOIN dis_dispatchhistory dd on ab.BusinessNoticeNo = dd.BusinessNoticeNo " +
            " INNER JOIN sy_units su on dd.TransferredUnit = su.id" +
            " INNER JOIN sy_units ss on dd.OperationUnitID = ss.id" +
            " INNER JOIN sy_units sus on ab.ProcessingUnit = sus.id" +
            " INNER JOIN sy_emp se on dd.OperatorID = se.id" +
            " where ab.businessNoticeNo = #{businessNoticeNo} and aw.jobType =1 and dd.status =1")
    AllAccBusAdmiWorkOr queryAllAccAdmiWorkOr(@Param("businessNoticeNo") String businessNoticeNo);


    /**
     * 查找AccBusinessAdmissibility
     * @param businessNoticeNo
     * @return
     */
    @Select("select ab.* from acc_businessadmissibility ab where ab.businessNoticeNo=#{businessNoticeNo}")
    AccBusinessAdmissibility findOneAccBusiness(@Param("businessNoticeNo") String businessNoticeNo);

    /**
     * 销毁history
     */
    @Update("update dis_dispatchhistory set status =0 where businessNoticeNo =#{businessNoticeNo}")
    void updDisHistory(@Param("businessNoticeNo") String businessNoticeNo);


    @Select("select ab.* from acc_businessadmissibility ab" +
            " left JOIN acc_workorder aw on ab.businessNoticeNo = aw.businessNoticeNo" +
            " where ab.SingleType ='人工受理'")
    AccBusinessAdmissibility queryIfWhen();

    /**
     * 根据empNo查找小件员信息
     */
    @Select("select * from sy_emp where empNo=#{empNo}")
    SyEmp queryEmpByNo(@Param("empNo") String empNo);

























}