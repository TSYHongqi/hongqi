package com.hongqi.springboot.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasZoneInfo implements Serializable {
    private static final long serialVersionUID = 5546806758388107652L;
    private Integer id;//编号
    private String zoneCode;//定区编码 唯一
    private String zoneName;//定区名称
    private int zonePeople;//定区负责人
    private String telPhone;//联系电话
    private int subordinateUnit;//所属单位	外键，对应到单位表ID


    /**
     * 受理会用到
     */
    private Integer eid; //编号
    private String empName; //员工姓名
    private String empNo; //工号
    private String queryPwd; //查台密码
    private int roleID; //角色ID
    private int empUnit; //员工所属单位
    private String remark; //备注
    private int  disabled; //禁用 1可使用 0已禁用
    private String perms;//按钮级权限
    private String PostalCode;//邮政编码
    private String phone;//（员工）联系人手机号

    /**
     *
     */
    private String city;//city

}
