package com.emall.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.emall.controller.alipay.AlipayProperties;
import org.springframework.stereotype.Service;

@Service
public class PayService {

    /**
     * 支付接口
     *
     * @param model
     * @return
     * @throws AlipayApiException
     */
    public String pay(AlipayTradeWapPayModel model) throws AlipayApiException {
        // 1、获得初始化的AlipayClient
        String serverUrl = AlipayProperties.getGatewayUrl();
        String appId = AlipayProperties.getAppId();
        String privateKey = AlipayProperties.getPrivateKey();
        String format = "json";
        String charset = AlipayProperties.getCharset();
        String alipayPublicKey = AlipayProperties.getPublicKey();
        String signType = AlipayProperties.getSignType();
        String returnUrl = AlipayProperties.getReturnUrl();
        String notifyUrl = AlipayProperties.getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);

        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数
        alipayRequest.setBizModel(model);
        // 3、请求支付宝进行付款，并获取支付结果

        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // 返回付款信息
      //  System.out.println(result);
        return result;
    }
}
