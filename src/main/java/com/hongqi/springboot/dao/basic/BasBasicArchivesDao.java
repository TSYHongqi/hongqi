/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BasBasicArchivesDao
 * Author:   TSYH
 * Date:     2020-01-08 16:07
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.dao.basic;

import com.hongqi.springboot.model.BasBasicArchiveSentry;
import com.hongqi.springboot.model.BasBasicArchives;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-08
 * @since 1.0.0
 */
@Repository
public interface BasBasicArchivesDao {
    /**
     *查询所有档案
     * @return
     */
    List<BasBasicArchives> findAll(@Param("id") int id, @Param("empName") String empName, @Param("name")
                                   String name, @Param("operationTime") String operationTime);

    /**
     * 查询详细档案列表
     */
    List<BasBasicArchiveSentry> findAllList(@Param("id") int id);


    /**
     * 新增基础档案
     */
    void addBasicArch(BasBasicArchives basBasicArchives);

    /**
     * 修改基础档案
     */
    void updBasicArch(BasBasicArchives basBasicArchives);

    /**
     * 根据id查询
     */
    BasBasicArchives queryBasArchById(@Param("id") Integer id);

    /**
     * 详细档案（start）
     */
    //新增档案
     void addBasicArchSentry(BasBasicArchiveSentry basBasicArchiveSentry);

     //修改档案
     void updBasicArchSentry(BasBasicArchiveSentry basBasicArchiveSentry);

     //根据id查询档案
     BasBasicArchiveSentry queryBasArSenById(@Param("id") Integer id);


    /**
     * end
     */

    /**
     * 作废
     */
    void delBasArSenById(@Param("id") Integer id);


}