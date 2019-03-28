package com.emall.service;

import com.emall.service.model.ShoppingcartModel;

import java.util.List;

/**
 * @author XYY
 * @date 2018/12/27 5:07 PM
 */
public interface ShoppingcartService {
    void addNewItem(ShoppingcartModel shoppingcartModel);
    int deleteItem(String shoppingcartId);
    int modifyAmount(ShoppingcartModel shoppingcartModel);
    List<ShoppingcartModel> getAllItem(String userId);
}
