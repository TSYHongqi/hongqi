package com.hongqi.springboot.dao.dispatch;


import com.hongqi.springboot.model.*;
import com.hongqi.springboot.util.SqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Repository
public interface SignInputDao {

    /**
     * 查询签收录入表
     */
     @SelectProvider(type = SqlProvider.class, method = "querySignInput")
    List<DisWorkordersign> findAll( @Param("empName") String empName,
                                    @Param("workSheetNo") String workSheetNo,
                                    @Param("start") String start,
                                    @Param("end") String end,
                                    @Param("signType") String signType,
                                    @Param("courierint") String courierint,
                                    @Param("name") String name,
                                    @Param("invalidateMark") String invalidateMark);
    /**
     * 查询所有取消签收表
     */
    @SelectProvider(type = SqlProvider.class,method = "queryDisCancelSign")
    List<DisCancelSign> findAllDis(@Param("appNo") String appNo,
                                   @Param("workSheetNo") String workSheetNo,
                                   @Param("status") String status,
                                   @Param("why") String why,
                                   @Param("appPeople") String appPeople,
                                   @Param("appTime") String appTime,
                                   @Param("appUnit") String appUnit);
       /**
       * 查询所有取消签收表和签收表
       */
       @Select("select * from acc_worksheet where worksheetNo=#{worksheetNo}")
       AccWorkSheet queryIfaccWor(@Param("worksheetNo") String worksheetNo);

       @Select(" select dw.*,su.name from dis_workordersign dw INNER JOIN sy_units su  " +
               "on dw.SignUnit = su.id  " +
               "where dw.status=1 and dw.worksheetNo = #{worksheetNo} ")
       DisWorkordersign queryAllDis(@Param("worksheetNo") String worksheetNo);

       @Select("select * from dis_cancelSign where worksheetNo = #{worksheetNo} ")
       DisCancelSign queryIf(@Param("worksheetNo") String worksheetNo);

    /**
     * 查询最新的申请单号
     */
    @Select("select max(appno) from dis_cancelSign")
    String queryMaxAppNo();

    /**
     * 新增取消签收单
     */
    @Insert("insert into dis_cancelSign(workSheetNo,appNo,appWhy,signType,signTime," +
            "signUnit,appUnit,appPeople,appTime,conPeople,conTime,conStatus,courierintName) values(" +
            " #{param1.workSheetNo},#{param1.appNo},#{param1.appWhy},#{param1.signType},#{param1.signTime}," +
            " #{param1.signUnit},#{param1.appUnit},#{param1.appPeople},now(),#{param1.conPeople}" +
            " ,now(), 1,#{param1.courierintName})")
    @Options(keyProperty="disCancelSign.id",useGeneratedKeys=true)
    void addDisCancelSign(@Param("disCancelSign") DisCancelSign disCancelSign);

    /**
     * 修改签收表，改为取消签收
     */
    @Update("update dis_workordersign set status = 2 where id=#{id}")
    void updDisWorkordersign(@Param("id") Integer id);

    /**
     * 同意
     */
    @Update("update dis_cancelSign set ConPeople =#{conPeople},ConStatus=2,ConTime=now(),status =2 where id= #{id}")
    void confirm(@Param("id") Integer id,@Param("conPeople") String conPeople);

    /**
     * 拒绝（修改）
     */
    @Update("update dis_cancelSign set why =#{why},ConStatus=3,status =2 where id= #{id}")
    void refuse(@Param("id") Integer id,@Param("why") String why);

    /**
     * 作废
     */
    @Update("update dis_cancelSign set status =2,conStatus=4 where id= #{id}")
    void delDisCancelSign(@Param("id") Integer id);


    /**
     * 查询sy_emp的员工号是否存在
     */
    @Select("select * from sy_emp where empNo = #{empNo} and Disabled=1")
    SyEmp ifEmpNo(@Param("empNo") String empNo);

    /**
     * 查询工单号workId
     */
    @Select("select ak.* from acc_worksheet aw inner join acc_workorder ak on aw." +
            "BusinessNoticeNo= ak.BusinessNoticeNo where ak.JobType !=3 and aw.worksheetNo =#{worksheetNo} ")
    AccWorkOrder findWorkId(@Param("worksheetNo") String worksheetNo);



    /**
     * 新增签收
     */
    @Insert("insert into dis_workordersign(workOrderID,workSheetNo,WorkOrderType,SignType,Courierint,CourierName,Recipient,SignUnit," +
            "SignTime,InvalidateMark,AbnormalMark,InputPersonCode,InputPersonID,InputID," +
            "InputTime,status) values(#{param1.workOrderID},#{param1.workSheetNo},#{param1.workOrderType},#{param1.signType},#{param1.courierint}," +
            "#{param1.courierName},#{param1.recipient},#{param1.signUnit},#{param1.signTime},2,null,#{param1.inputPersonCode},#{param1.inputPersonID},#{param1.inputID},now(),1)")
    @Options(keyProperty="disWorkordersign.id",useGeneratedKeys=true)
    void addSign(@Param("disWorkordersign") DisWorkordersign disWorkordersign);

    /**
     * 修改工作单为已签收
     */
    @Select("update acc_worksheet set disabled = 2")
    void updSign(@Param("workSheetNo") String workSheetNo);


    /**
     * 查询签收人的单位
     */
    @Select("select * from sy_emp where empNo=(select Recipient from dis_workordersign)")
    SyEmp queryUnits();

    /**
     * 修改签收单
     */
    @Update("update dis_workordersign set recipient = #{recipient},recipientName=#{recipientName},signTime= #{signTime} " +
            "where workSheetNo = #{workSheetNo} ")
    void updateSign(@Param("recipient") String recipient,@Param("signTime") Date signTime,
                    @Param("workSheetNo")String workSheetNo,@Param("recipientName") String recipientName);

}