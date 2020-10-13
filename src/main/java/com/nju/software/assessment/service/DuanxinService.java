package com.nju.software.assessment.service;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.util.Vector;

@org.springframework.stereotype.Service
public class DuanxinService {
    //发送短信验证码
    public Integer sendVerifyCode(String phone,String code){
        String content = "【法官测评系统】您的验证码为"+code+",3分钟之内有效,请尽快登录,请勿泄露他人。";
//        content = content.replaceFirst("XXXXXX",code);
        System.out.println(content);
        return send(phone,content);
    }

    public Integer sendNotify(String phone,String date){
        String content = "【法官测评系统】您有新的投票通知，请在办公办案平台中登录测评系统查看，截止时间为"+date.substring(0,11)+"零点,逾期未处理自动视为弃权。";
        System.out.println(content);
        return send(phone,content);
    }

    public  Integer send(String phone, String content) {
        String url = "http://130.1.1.156:8181/ws/sms?wsdl";// 提供接口的地址
        String soapaction = "http://webservice.core.smsgate.thunisoft.com/"; // 域名，这是在server定义的

        Integer result = 0;

        Service service = new Service();
        try {
            System.out.println("进入send方法");
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(url));

            call.setOperationName(new QName(soapaction, "sendSms")); // 设置要调用哪个方法


            call.addParameter("phones", // 参数名
                    XMLType.XSD_STRING,// 参数类型:String
                    ParameterMode.IN);// 参数模式：'IN' or 'OUT'
            call.addParameter("content", // 参数名
                    XMLType.XSD_STRING,// 参数类型:String
                    ParameterMode.IN);
            call.addParameter("user", // 参数名
                    XMLType.XSD_STRING,// 参数类型:String
                    ParameterMode.IN);
            call.addParameter("password", // 参数名
                    XMLType.XSD_STRING,// 参数类型:String
                    ParameterMode.IN);

            call.setReturnType(XMLType.XSD_INTEGER);// （标准的类型）
            call.setReturnType(new QName(soapaction, "sendSms"), Vector.class); // 要返回的数据类型（自定义类型）

            call.setUseSOAPAction(true);
            call.setSOAPActionURI(soapaction + "sendSms");

            Vector v = (Vector) call.invoke(new Object[] { phone, content,
                    "nd", "nd" });// 调用方法并传递参数
            result = Integer.valueOf((String) v.get(0));
            System.out.println(result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
}
