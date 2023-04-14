package com.ds.nas.cloud.message.channel.sms.client;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author ds
 * @date 2023/4/6
 * @description 腾讯短信客户端
 */
@Slf4j
@Component(ClientName.TENCENT_CLIENT)
public class TencentSMSClient implements SMSClient {

    private SmsClient client;

    @Value("${config.sms.tx.sdkAppId}")
    private String sdkAppId;

    @Value("${config.sms.tx.secretId}")
    private String secretId;

    @Value("${config.sms.tx.secretKey}")
    private String secretKey;

    @Value("${config.sms.tx.signName}")
    private String signName;

    @Value("${config.sms.tx.templateId}")
    private String templateId;

    @PostConstruct
    private void init() {
        createClient();
        SmsClientContext.register(ClientName.ALI_CLIENT, this);
    }

    @Override
    public boolean send(String phone, List<String> params) {
        /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
         * 你可以直接查询SDK源码确定接口有哪些属性可以设置
         * 属性可能是基本类型，也可能引用了另一个数据结构
         * 推荐使用IDE进行开发，可以方便的跳转查阅各个接口和数据结构的文档说明 */
        SendSmsRequest req = new SendSmsRequest();
        /* 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId，示例如1400006666 */
        req.setSmsSdkAppId(sdkAppId);
        /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名 */
        req.setSignName(signName);
        /* 模板 ID: 必须填写已审核通过的模板 ID */
        req.setTemplateId(templateId);
        /* 模板参数: 模板参数的个数需要与 TemplateId 对应模板的变量个数保持一致，若无模板参数，则设置为空 */
        // todo 待改进
        String[] templateParam = new String[]{params.get(0), params.get(1)};
        req.setTemplateParamSet(templateParam);

        /* 下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
         * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号 */
        String[] phoneNumberSet = {"+86" + phone};
        req.setPhoneNumberSet(phoneNumberSet);

        /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
         * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
        SendSmsResponse res;
        try {
            res = client.SendSms(req);
        } catch (TencentCloudSDKException e) {
            log.error("发送失败...", e);
            return false;
        }

        log.info("发送成功... {}", res);
        // 返回json格式的字符串回包
        return true;
    }

    private void createClient() {
        Credential cred = new Credential(secretId, secretKey);
        // 实例化一个http选项，可选，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        /* SDK默认使用POST方法。
         * 如果你一定要使用GET方法，可以在这里设置。GET方法无法处理一些较大的请求 */
        httpProfile.setReqMethod("POST");
        /* SDK有默认的超时时间，非必要请不要进行调整
         * 如有需要请在代码中查阅以获取最新的默认值 */
        httpProfile.setConnTimeout(60);
        /* 指定接入地域域名，默认就近地域接入域名为 sms.tencentcloudapi.com ，也支持指定地域域名访问，例如广州地域的域名为 sms.ap-guangzhou.tencentcloudapi.com */
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        /* SDK默认用TC3-HMAC-SHA256进行签名
         * 非必要请不要修改这个字段 */
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);
        /* 实例化要请求产品(以sms为例)的client对象
         * 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，支持的地域列表参考 https://cloud.tencent.com/document/api/382/52071#.E5.9C.B0.E5.9F.9F.E5.88.97.E8.A1.A8 */
        client = new SmsClient(cred, "ap-guangzhou", clientProfile);
    }

}
