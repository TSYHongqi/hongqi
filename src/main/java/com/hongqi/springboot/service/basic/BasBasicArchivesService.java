/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BasBasicArchivesService
 * Author:   TSYH
 * Date:     2020-01-08 16:40
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.basic;

import com.hongqi.springboot.model.BasBasiCarchiveSentry;
import com.hongqi.springboot.model.BasBasiCarchives;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-01-08
 * @since 1.0.0
 */
public interface BasBasicArchivesService {
    /**
     *查询所有档案
     * @return
     */
    List<BasBasiCarchives> findAll(int id,String empName,String name, String operationTime);

    /**
     * 查询详细档案列表
     */
    List<BasBasiCarchiveSentry> findAllList(int id);


}