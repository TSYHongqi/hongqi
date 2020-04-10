/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ProTaskDao
 * Author:   TSYH
 * Date:     2020-02-06 13:35
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.dao.dispatch;

import com.hongqi.springboot.model.DisPropagandatask;
import com.hongqi.springboot.util.SqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-06
 * @since 1.0.0
 */
@Repository
public interface ProTaskDao {

    /**
     * 查询宣传任务
     * @param outline
     * @param releaseTime
     * @param failureTime
     * @param Status
     * @return
     */
    @SelectProvider(type = SqlProvider.class, method = "queryPropagandatask")
    List<DisPropagandatask> queryAll(@Param("outline") String outline,
                                                       @Param("releaseTime") String releaseTime,
                                                       @Param("failureTime") String failureTime,
                                                       @Param("status") String Status);

    /**
     * 新增宣传任务
     */
    @Insert("insert into dis_propagandatask(outline,releaseTime,failureTime,status,content," +
            "updateTime,operatorID,operationUnitID) values(#{param1.outline},#{param1.releaseTime},#{param1.failureTime}," +
            "#{param1.status},#{param1.content},now(),#{param1.operatorID},#{param1.operationUnitID})")
    @Options(keyProperty="disPropagandatask.id",useGeneratedKeys=true)
    void addDisProTask(@Param("disPropagandatask")DisPropagandatask disPropagandatask);

    /**
     * 根据id查询
     */
    @Select("select dp.*,se.empName,su.name from" +
            " dis_propagandatask dp INNER JOIN sy_emp se on dp.OperatorID=se.id INNER JOIN sy_units su on dp.OperationUnitID = su.id" +
            " where dp.id = #{id}")
    DisPropagandatask queryDisProTaskById(@Param("id") Integer id);


    /**
     * 修改宣传任务
     */
    @Update("update dis_propagandatask set outline =#{param1.outline},releaseTime=#{param1.releaseTime}," +
            " failureTime = #{param1.failureTime},status = #{param1.status},content = #{param1.content} where id = #{param1.id}")
    void updPropagandaTask(@Param("disPropagandatask") DisPropagandatask disPropagandatask);




















}