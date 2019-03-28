package com.emall.service;

import com.emall.dao.OrderDOMapper;
import com.emall.dao.OrderItemDOMapper;
import com.emall.dataobject.OrderDO;
import com.emall.dataobject.OrderItemDO;
import com.emall.queryobj.AllOrders;
import com.emall.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private OrderItemDOMapper orderItemDOMapper;

    public List<AllOrders> getAllOrders(String userId){
        List<OrderDO> orderDOs = orderDOMapper.selectAllOrdersByUserId(userId);
        List<OrderItemDO> orderItemDOs = orderItemDOMapper.selectAllOrdersByUserId(userId);

        List<AllOrders> ans = new ArrayList<>();
        int j = 0;
        int size = orderItemDOs.size();
        String orderId;
        for (int i = 0; i < orderDOs.size(); i++) {
            AllOrders orders = new AllOrders();
            orders.setOrderDO(orderDOs.get(i));

            orderId = orderDOs.get(i).getOrderId();
            List<OrderItemDO> cur = new ArrayList<>();
            while (j < size && orderItemDOs.get(j).getOrderId().equals(orderId)) {
                cur.add(orderItemDOs.get(j));
                ++j;
            }
            orders.setOrderItemDOs(cur);
            ans.add(orders);
        }

        return ans;
    }

    @Transactional
    public String createOrder(OrderDO orderDO, List<OrderItemDO> orderItemDOs){
        //userId从session里面获取
        //待续
        //  String userId=httpServletRequest.getSession().getAttribute("userId").toString();
        //userId还是从前端发送过来吧
        //orderId雪花码生成
        SnowFlake snowFlake=SnowFlake.getInstance();
        String orderId=String.valueOf(snowFlake.nextId());

        //设置订单的orderId
        orderDO.setOrderId(orderId);
        //订单状态,1代表待付款
       orderDO.setOrderStatus(1);
        //设置订单创建时间
        Date now=new Date();
        orderDO.setCreateTime(now);
        //向数据库里面插入一条em_order的记录
        orderDOMapper.insert(orderDO);
        OrderItemDO orderItemDO=null;
        for (int i = 0; i <orderItemDOs.size() ; i++) {
            //生成order_item的雪花码
            orderItemDO=orderItemDOs.get(i);
            orderItemDO.setOrderItemId(String.valueOf(snowFlake.nextId()));
            orderItemDO.setUserId(orderDO.getUserId());
            orderItemDO.setOrderId(orderDO.getOrderId());
            orderItemDO.setCreateTime(now);
            //向em_order_item里面插入orderItemDOs长度的记录
            orderItemDOMapper.insert(orderItemDO);
        }

        //返回orderId
        return orderId;
    }
}
