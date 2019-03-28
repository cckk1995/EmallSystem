package com.emall.controller;

import com.emall.dataobject.OrderDO;
import com.emall.dataobject.OrderItemDO;
import com.emall.queryobj.AllOrders;
import com.emall.queryobj.OrderInfo;
import com.emall.response.CommonReturnType;
import com.emall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李良俊
 */
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class OrderController extends BaseController {


    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/allOrders/{userId}", method = RequestMethod.GET)
    public CommonReturnType getAllOrders(@PathVariable String userId) {
        List<AllOrders> data = orderService.getAllOrders(userId);
        return CommonReturnType.create(data);
    }

    @PostMapping(value = "/createOrders")
    public CommonReturnType createOrders(@RequestBody OrderInfo orderInfo) {
        //从解析的json里面获取对象
        OrderDO orderDO = orderInfo.getOrderDO();
        List<OrderItemDO> orderItemDOs = orderInfo.getOrderItemDOs();
        String orderId = orderService.createOrder(orderDO, orderItemDOs);
        //返回订单号给前端显示
        return CommonReturnType.create(orderId);
    }

}
