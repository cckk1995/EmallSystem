package com.emall.service;

import com.emall.error.BusinessException;
import com.emall.response.CommonReturnType;

public interface ItemService {
    CommonReturnType getItemList() throws BusinessException;
    CommonReturnType getItemDetil(String itemId) throws BusinessException;
    CommonReturnType getItemPriceAndStock(String itemId,String symbol) throws BusinessException;
    CommonReturnType getTopSales(int number) throws BusinessException;
    CommonReturnType getIntroImg(String itemId) throws BusinessException;
    CommonReturnType getComments(String itemId) throws BusinessException;
}
