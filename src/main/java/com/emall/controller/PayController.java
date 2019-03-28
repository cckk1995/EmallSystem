package com.emall.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.emall.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping(value = "/aliPay", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String alipay(HttpServletRequest request) throws AlipayApiException {

        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(request.getParameter("outTradeNo"));
        model.setSubject(request.getParameter("subject"));
        model.setTotalAmount(request.getParameter("totalAmount"));
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        return payService.pay(model);
    }
}
