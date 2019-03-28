package com.emall.queryobj;

import com.emall.dataobject.OrderDO;
import com.emall.dataobject.OrderItemDO;

import java.util.List;

public class AllOrders {
     private OrderDO orderDO;

    public OrderDO getOrderDO() {
        return orderDO;
    }

    public void setOrderDO(OrderDO orderDO) {
        this.orderDO = orderDO;
    }

    public List<OrderItemDO> getOrderItemDOs() {
        return orderItemDOs;
    }

    public void setOrderItemDOs(List<OrderItemDO> orderItemDOs) {
        this.orderItemDOs = orderItemDOs;
    }

    private List<OrderItemDO> orderItemDOs;
}
