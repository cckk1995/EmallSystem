package com.emall.service.impl;

import com.emall.dao.ShoppingCartDOMapper;
import com.emall.dataobject.ShoppingCartDO;
import com.emall.service.ShoppingcartService;
import com.emall.service.model.ShoppingcartModel;
import com.emall.utils.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XYY
 * @date 2018/12/27 10:01 PM
 */
@Service
public class ShoppingcartServiceImpl implements ShoppingcartService {

    public static SnowFlake snowFlake;

    @Autowired
    private ShoppingCartDOMapper shoppingCartDOMapper;

    @Override
    public void addNewItem(ShoppingcartModel shoppingcartModel) {
        if (snowFlake == null) {
            snowFlake = new SnowFlake(4, 0);
        }
        ShoppingCartDO existedItem = shoppingCartDOMapper.selectByItemIdAndStockId(shoppingcartModel.getItemId(), shoppingcartModel.getStockId());
        if (existedItem != null) {
            int amount = shoppingcartModel.getAmount() + existedItem.getAmount();
            existedItem.setAmount(amount);
            shoppingCartDOMapper.updateByPrimaryKey(existedItem);
        }
        else {
            shoppingcartModel.setCartId(Long.toString(snowFlake.nextId()));
            ShoppingCartDO shoppingCartDO = convertDataObjectFromModel(shoppingcartModel);
            shoppingCartDOMapper.insert(shoppingCartDO);
        }
    }

    @Override
    public int deleteItem(String shoppingcartId) {
        return shoppingCartDOMapper.deleteByPrimaryKey(shoppingcartId);
    }

    @Override
    public int modifyAmount(ShoppingcartModel shoppingcartModel) {
        ShoppingCartDO shoppingCartDO = convertDataObjectFromModel(shoppingcartModel);
        return shoppingCartDOMapper.updateByPrimaryKeySelective(shoppingCartDO);
    }

    @Override
    public List<ShoppingcartModel> getAllItem(String userId) {
        List<ShoppingCartDO> shoppingCartDOList = shoppingCartDOMapper.selectByUserId(userId);
        List<ShoppingcartModel> shoppingcartModelList = shoppingCartDOList.stream().map(shoppingCartDO -> {
            ShoppingcartModel shoppingcartModel = convertModelFromDataObject(shoppingCartDO);
            return shoppingcartModel;
        }).collect(Collectors.toList());
        return shoppingcartModelList;
    }

    private ShoppingcartModel convertModelFromDataObject(ShoppingCartDO shoppingCartDO) {
        if (shoppingCartDO == null) {
            return null;
        }
        ShoppingcartModel shoppingcartModel = new ShoppingcartModel();
        BeanUtils.copyProperties(shoppingCartDO, shoppingcartModel);
        return shoppingcartModel;
    }

    private ShoppingCartDO convertDataObjectFromModel(ShoppingcartModel shoppingcartModel) {
        if (shoppingcartModel == null) {
            return null;
        }
        ShoppingCartDO shoppingCartDO = new ShoppingCartDO();
        BeanUtils.copyProperties(shoppingcartModel, shoppingCartDO);
        return shoppingCartDO;
    }
}
