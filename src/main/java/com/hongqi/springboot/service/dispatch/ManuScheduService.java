package com.hongqi.springboot.service.dispatch;


import javax.servlet.http.HttpSession;

public interface ManuScheduService {
    /**
     * 查询人工调度信息
     */
    String queryManuSchede(String page,String limit,String businessNoticeNo,String reservationTime);

    /**
     * 查找所有单位
     */
   String queryAllUnits();

    /**
     * 查找单位下的员工号
     */
    String queryEmpbyUid(Integer empUnit);
    /**
     * 分配
     */
    String distribution(String name, String empNo, Integer id, HttpSession session);

    /**
     * 查找人工调度详情
     */
    String findAllDetailByBus(String businessNoticeNo);

    /**
     * 查找业务通知单
     */
    void findOneAccBusiness(HttpSession session,String businessNoticeNo);



}
