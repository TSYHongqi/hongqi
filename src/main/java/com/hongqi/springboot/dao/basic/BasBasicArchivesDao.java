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

import com.hongqi.springboot.model.BasBasiCarchiveSentry;
import com.hongqi.springboot.model.BasBasiCarchives;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
    List<BasBasiCarchives> findAll(@Param("id") int id,@Param("empName") String empName,@Param("name")
                                   String name,@Param("operationTime") String operationTime);

    /**
     * 查询详细档案列表
     */
    List<BasBasiCarchiveSentry> findAllList(@Param("id") int id);

}