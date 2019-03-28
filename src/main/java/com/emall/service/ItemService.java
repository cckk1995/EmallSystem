package com.emall.service;

import com.emall.controller.viewobject.GoodsListVO;
import com.emall.dataobject.ItemDO;
import com.emall.error.BusinessException;
import com.emall.response.CommonReturnType;

import java.util.List;

public interface ItemService {
    GoodsListVO getGoodsListVOByItemId(String itemId) throws BusinessException;
    CommonReturnType getItemList() throws BusinessException;
    CommonReturnType getItemDetil(String itemId) throws BusinessException;
    CommonReturnType getItemPriceAndStock(String itemId, String symbol) throws BusinessException;
    CommonReturnType getTopSales(int number) throws BusinessException;
    CommonReturnType getIntroImg(String itemId) throws BusinessException;
    CommonReturnType getComments(String itemId) throws BusinessException;
    List<GoodsListVO> getItemByKeyWord(String keyword) throws BusinessException;
}
