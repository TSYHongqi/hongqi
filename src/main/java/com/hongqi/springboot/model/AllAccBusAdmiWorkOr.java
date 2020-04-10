/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AllAccBusAdmiWorkOr
 * Author:   TSYH
 * Date:     2020-02-04 23:20
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-04
 * @since 1.0.0
 */
@Data
public class AllAccBusAdmiWorkOr implements Serializable {

    private static final long serialVersionUID = -2002230119675120670L;
    private Integer aid;//编号
    private String businessNoticeNo;//业务通知单号
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date reservationTime;//预约收件时间
    private String customName;//客户名称
    private String pickupAddress;//取件地址
    private String customCode;//客户编号
    private String linkman;//联系人
    private String telPhone;//电话
    private double weight;//重量
    private String volume;//体积
    private String importantHints;//重要提示
    private String arriveCity;//到达城市
    private int pickerInfo;//取货人员信息
    private String sendAddress;//派送地址
    private int processingUnit;//处理单位	外键，对应到单位表ID
    private int notificationSource;//通知单来源
    private int customNoModifyFlag;//客户单号修改标志
    private String singleType;//分单类型
    private int packagesNum;//件数
    private double actualWeight;//计费重量
    private double packingFee;//包装费
    private int actualPacking;//实际包装 1.木箱、2.纸箱

    private Integer id;//编号
    private String jobNo;//工单号
    private int jobType;//工单类型	1:新,2:追,3:销
    private int pickupStatus;//取件状态	1:新单,2:已通知,3:已确认4:已取件,5:已取消
    private int  shortMessageint;//短信序号;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date workGenerationTime;//工单生成时间
    private int afterSingleNum;//追单次数	新单默认为0
    private int smallMemberNum;//小件员编号
    private int pickupUnit;//取件单位	外键，对应到单位表ID
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date pickupTime;//取件时间
    private String sortingCode;//分拣编码
    private String city;

    private String dispatchSequence;//调度序号
    private Integer transferredUnit;//调入单位
    private Integer operatorID;// 暂无
    private Integer operationUnitID;//操作单位
    private Date operationTime;//操作时间
    private String remark;//备注
    private Integer status;//状态
    private String reason;//退回原因

    private String name;//所在单位
    private String uname;//操作单位
    private String empName;//操作人
    private String pname;//处理单位
}