/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MoblieMessageUtil
 * Author:   TSYH
 * Date:     2020-02-14 16:00
 * Description:
 * History:
 * <author>     <time>       <version>     <desc>
 * 作者姓名    修改时间     版本号       描述
 */
package com.hongqi.springboot.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author TSYH
 * @create 2020-02-14
 * @since 1.0.0
 */
public class MoblieMessageUtil {

    // 产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static String accessKeyId = "LTAI4FdapXkCTCypM3yryK1a";
    private static String accessKeySecret = "u6vufgPe622QvjpPbv22U9SmhK7I5E";
    private static String signName = "易趣网";
    private static String identifyingTempleteCode = "u6vufgPe622QvjpPbv22U9SmhK7I5E"; //发验证码
    private static String registTempleteCode = "SMS_183795217";

    public static void init(String accessKeyId, String accessKeySecret, String signName, String identifyingTempleteCode,
                            String registTempleteCode) {
        MoblieMessageUtil.accessKeyId = accessKeyId;
        MoblieMessageUtil.accessKeySecret = accessKeySecret;
        MoblieMessageUtil.signName = signName;
        MoblieMessageUtil.identifyingTempleteCode = identifyingTempleteCode;
        MoblieMessageUtil.registTempleteCode = registTempleteCode;
    }

    public static void main(String[] args) {
        MoblieMessageUtil.init("LTAI4FdapXkCTCypM3yryK1a", "u6vufgPe622QvjpPbv22U9SmhK7I5E", "易趣网", "SMS_110",
                "SMS_183795217");
        // 发短信验证码
        SendSmsResponse response = MoblieMessageUtil.sendIdentifyingCode("手机号", "123456");
       /* System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());*/

        response = MoblieMessageUtil.sendNewUserNotice("17347370286", 123456, "新",
                "YWD10001","黎明","朝阳区19号","13348773243",
                "12","12","丽江区22号","不要碰水");
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());

    }

    public static SendSmsResponse sendSms(String mobile, String templateParam, String templateCode)
            throws ClientException {

        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();

        // 必填:待发送手机号
        request.setPhoneNumbers(mobile);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);

        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParam);

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    public static SendSmsResponse sendNewUserNotice(String mobile, Integer shortMessageint,
                                                    String state,String businessNoticeNo,String customName,
                                                    String pickupAddress,String telPhone,String weight,String volume,
                                                    String sendAddress,String importantHints) {
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = sendSms(mobile,"{ \"shortMessageint\":\"" + shortMessageint + "\", \"shortMessageint\":\"" + shortMessageint + "\",\"state\":\"" + state + "\",\"businessNoticeNo\":\"" + businessNoticeNo + "\",\"customName\":\"" + customName +
                            "\",\"pickupAddress\":\"" + pickupAddress + "\",\"telPhone\":\"" + telPhone + "\" ,\"weight\":\"" + weight + "\",\"volume\":\"" + volume + "\" ,\"sendAddress\":\"" + sendAddress + "\",\"importantHints\":\"" + importantHints + "\"}"
                    , registTempleteCode);
        } catch (ClientException e) {

        }
        return sendSmsResponse;
    }

    //发送验证码
    public static SendSmsResponse sendIdentifyingCode(String mobile, String code) {
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = sendSms(mobile, "{\"code\":\"" + code + "\"}", identifyingTempleteCode);
        } catch (ClientException e) {

        }
        return sendSmsResponse;
    }



}