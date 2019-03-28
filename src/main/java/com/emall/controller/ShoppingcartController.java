package com.emall.controller;

import com.emall.controller.viewobject.ShoppingcartVO;
import com.emall.error.BusinessException;
import com.emall.error.EmBusinessError;
import com.emall.response.CommonReturnType;
import com.emall.service.ShoppingcartService;
import com.emall.service.model.ShoppingcartModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XYY
 * @date 2018/12/27 4:31 PM
 */
@Controller("shoppringcart")
@RequestMapping("/shoppingcart")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ShoppingcartController extends BaseController {

    @Autowired
    private ShoppingcartService shoppingcartService;

    @RequestMapping(value = "/addnew", method = RequestMethod.POST)
    @ResponseBody
    public CommonReturnType addNewShoppingcartItem(@RequestParam(name = "itemId") String itemId,
                                                   @RequestParam(name = "itemTitle") String itemTitle,
                                                   @RequestParam(name = "userId") String userId,
                                                   @RequestParam(name = "stockId") Integer stockId,
                                                   @RequestParam(name = "attrVals") String attrVals,
                                                   @RequestParam(name = "attrImg") String attrImg,
                                                   @RequestParam(name = "price") Double price,
                                                   @RequestParam(name = "amount") Integer amount) throws BusinessException {
        if (StringUtils.isEmpty(itemId) || StringUtils.isEmpty(userId)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        ShoppingcartModel shoppingcartModel = new ShoppingcartModel();
        shoppingcartModel.setItemId(itemId);
        shoppingcartModel.setItemTitle(itemTitle);
        shoppingcartModel.setUserId(userId);
        shoppingcartModel.setStockId(stockId);
        shoppingcartModel.setAttrImg(attrImg);
        shoppingcartModel.setAttrVals(attrVals);
        shoppingcartModel.setAmount(amount);
        shoppingcartModel.setPrice(BigDecimal.valueOf(price));
        shoppingcartService.addNewItem(shoppingcartModel);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    @ResponseBody
    public CommonReturnType getAllShoppingcartItem(@RequestParam(name = "id") String userId) throws BusinessException {
        if (StringUtils.isEmpty(userId)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<ShoppingcartModel> shoppingcartModelList = shoppingcartService.getAllItem(userId);
        List<ShoppingcartVO> shoppingcartVOList = shoppingcartModelList.stream().map(shoppingcartModel -> {
            ShoppingcartVO shoppingcartVO = convertShoppingcartVOFromModel(shoppingcartModel);
            return shoppingcartVO;
        }).collect(Collectors.toList());
        return CommonReturnType.create(shoppingcartVOList);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturnType deleteShoppingcartItem(@RequestParam(name = "id") String cartId) throws BusinessException {
        if (StringUtils.isEmpty(cartId)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        int affectRows = shoppingcartService.deleteItem(cartId);
        if (affectRows == 0) {
            throw new BusinessException(EmBusinessError.SHOPPINGCART_ITEM_NOT_EXIST);
        }
        return CommonReturnType.create(null);
    }

    @RequestMapping("/modifyamount")
    @ResponseBody
    public CommonReturnType modifyAmount(@RequestParam(name = "id") String cartId,
                                         @RequestParam(name = "amount") Integer amount) throws BusinessException {
        if (StringUtils.isEmpty(cartId) || amount <= 0) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        ShoppingcartModel shoppingcartModel = new ShoppingcartModel();
        shoppingcartModel.setCartId(cartId);
        shoppingcartModel.setAmount(amount);
        int affectRows = shoppingcartService.modifyAmount(shoppingcartModel);
        if (affectRows == 0) {
            throw new BusinessException(EmBusinessError.SHOPPINGCART_ITEM_NOT_EXIST);
        }
        return CommonReturnType.create(null);
    }

    private ShoppingcartVO convertShoppingcartVOFromModel(ShoppingcartModel shoppingcartModel) {
        if (shoppingcartModel == null) {
            return null;
        }
        ShoppingcartVO shoppingcartVO = new ShoppingcartVO();
        BeanUtils.copyProperties(shoppingcartModel, shoppingcartVO);
        return shoppingcartVO;
    }
}
