/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ProTaskService
 * Author:   TSYH
 * Date:     2020-02-06 13:36
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.service.dispatch;

import com.hongqi.springboot.model.DisPropagandatask;

import javax.servlet.http.HttpSession;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-06
 * @since 1.0.0
 */
public interface ProTaskService {

    /**
     * 查询宣传任务
     */
    String findDisProTask(String page,String limit,String outline,String releaseTime,
                          String failureTime,String status);

    /**
     * 新增宣传任务
     */
    void addDisProTask(DisPropagandatask disPropagandatask, HttpSession session);


    /**
     * 根据id查询
     */
    String findDisProTask(Integer id);

    /**
     * 修改宣传任务
     */
    void updPropagandaTask(DisPropagandatask disPropagandatask);
}