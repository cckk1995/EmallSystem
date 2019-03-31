package com.emall.service.impl;

import com.emall.controller.viewobject.*;
import com.emall.dao.*;
import com.emall.dataobject.*;
import com.emall.error.BusinessException;
import com.emall.error.EmBusinessError;
import com.emall.response.CommonReturnType;
import com.emall.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.stream.Collectors;

/**
 * Create by chaikai on 2019/01/15
 */
@Service("ItemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemAttrKeyDOMapper itemAttrKeyDOMapper;

    @Autowired
    private ItemAttrValDOMapper itemAttrValDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    @Autowired
    private BuyerCommentDOMapper buyerCommentDOMapper;

    @Autowired
    private OrderItemDOMapper orderItemDOMapper;

    @Autowired
    private UserDOMapper userDOMapper;

    /**
     *
     * @return CommonReturnType
     * @throws BusinessException
     */
    @Override
    public CommonReturnType getItemList(){
        List<ItemDO> itemDOS = itemDOMapper.getItems();
        List<GoodsListVO> goodsListVOS = new ArrayList<>();
        for(int i = 0; i < itemDOS.size(); i++){
            goodsListVOS.add(convertItemDoToGoodsVo(itemDOS.get(i)));
        }
        return CommonReturnType.create(goodsListVOS);
    }

    /**
     *
     * @param itemId 商品的ID（雪花码）
     * @return  CommonReturnType
     * @throws BusinessException
     */
    @Override
    public CommonReturnType getItemDetil(String itemId){
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(itemId);
        ItemShowVO itemShowVO = new ItemShowVO();
        itemShowVO.setItemId(itemId);
        itemShowVO.setItemTitle(itemDO.getItemTitle());
        itemShowVO.setItemSales(itemDO.getItemSales());
        itemShowVO.setItemDetailImage(itemDO.getItemDetailImage());
        itemShowVO.setItemIntroImage(itemDO.getItemIntroImage());
        itemShowVO.setMeal(getMeal(itemId));
        return CommonReturnType.create(itemShowVO);
    }
    @Override
    public CommonReturnType getIntroImg(String itemId){
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(itemId);
        return CommonReturnType.create(itemDO.getItemIntroImage());
    }
    /**
     *
     * @param itemId 商品的id
     * @param symbol 属性标签集合
     * @return CommonReturnType
     * @throws BusinessException
     */
    @Override
    public CommonReturnType getItemPriceAndStock(String itemId, String symbol) {
        Map<String, String> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("symbol", symbol);
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemIdAndSymbol(map);
        if(itemStockDO==null){
            return CommonReturnType.create(null,"false");
        }else{
            return CommonReturnType.create(itemStockDO);
        }
    }

    /**
     *
     * @param number 热销商品的数量
     * @return CommonReturnType
     * @throws BusinessException
     */
    @Override
    public CommonReturnType getTopSales(int number){
        List<ItemDO> itemDOS = itemDOMapper.getTopSails(number);
        List<HotItemVO> hotItemVOS = new ArrayList<>();
        for(int i = 0; i < itemDOS.size(); i++){
            HotItemVO hotItemVO = new HotItemVO();
            String itemId = itemDOS.get(i).getItemId();
            hotItemVO.setItemId(itemId);
            hotItemVO.setImg(itemDOS.get(i).getItemMainImage());
            hotItemVO.setPrize(itemStockDOMapper.getMinPrice(itemId));
            hotItemVO.setSale(itemDOS.get(i).getItemSales());
            hotItemVOS.add(hotItemVO);
        }
        return CommonReturnType.create(hotItemVOS);
    }

    /**
     *
     * @param itemId 商品的id
     * @return List<MealDO> 返回商品的属性列表
     * @throws BusinessException
     */

    public List<MealVO> getMeal(String itemId){
        List<ItemAttrKeyDO> itemAttrKeyDOS = itemAttrKeyDOMapper.selectByItemId(itemId);
        List<MealVO> mealVOS = new ArrayList<>() ;
        for(int i = 0; i < itemAttrKeyDOS.size(); i++){
            MealVO mealDO = new MealVO();
            int attrKeyId = itemAttrKeyDOS.get(i).getAttrKeyId();
            mealDO.setAttrId(attrKeyId);
            mealDO.setItemId(itemAttrKeyDOS.get(i).getItemId());
            mealDO.setAttrName(itemAttrKeyDOS.get(i).getAttrName());
            List<ItemAttrValDO> itemAttrValDOS = itemAttrValDOMapper.selectByAttrKeyId(attrKeyId);
            List<ItemAttrValVO> itemAttrValVOList = itemAttrValDOS.stream().map(itemAttrValDO -> {
                ItemAttrValVO itemAttrValVO = convertItemAttrValDOToItemAttrValVO(itemAttrValDO);
                return itemAttrValVO;
            }).collect(Collectors.toList());
            mealDO.setValue(itemAttrValVOList);
            mealVOS.add(mealDO);
        }
        return mealVOS;
    }

    /**
     *
     * @param itemId 商品的Id
     * @return  CommonReturnType
     * @throws BusinessException
     */
    @Override
    public CommonReturnType getComments(String itemId){
        List<OrderItemDO> orderItemDOS = orderItemDOMapper.selectByItemId(itemId);
        List<CommentVO> commentVOS = new ArrayList<>();
        for(OrderItemDO orderItemDO : orderItemDOS){
            CommentVO commentVO = new CommentVO();
            String userId = orderItemDO.getUserId();
            String orderItemId = orderItemDO.getOrderItemId();
            commentVO.setGoodsMeal(orderItemDO.getAttrVal());
            BuyerCommentDO buyerCommentDO = buyerCommentDOMapper.selectByOrderItemId(orderItemId);
            if (buyerCommentDO != null) {
                commentVO.setContent(buyerCommentDO.getComment());
                commentVO.setCreateTime(buyerCommentDO.getCreateTime());
                int value = buyerCommentDO.getCommentType();
                switch (value) {
                    case 1:
                        commentVO.setValues(5);
                        break;
                    case 2:
                        commentVO.setValues(3);
                        break;
                    case 3:
                        commentVO.setValues(1);
                        break;
                }
            }
            UserDO userDO = userDOMapper.selectByPrimaryKey(userId);
            commentVO.setUsername(userDO.getUserName());
            commentVOS.add(commentVO);
        }
        return CommonReturnType.create(commentVOS);
    }

    private ItemAttrValVO convertItemAttrValDOToItemAttrValVO(ItemAttrValDO itemAttrValDO) {
        ItemAttrValVO itemAttrValVO = new ItemAttrValVO();
        BeanUtils.copyProperties(itemAttrValDO, itemAttrValVO);
        itemAttrValVO.setSelect(false);
        return itemAttrValVO;
    }
    private GoodsListVO convertItemDoToGoodsVo(ItemDO itemDO){
        GoodsListVO goodsListVO = new GoodsListVO();
        String itemId = itemDO.getItemId();
        goodsListVO.setItemId(itemId);
        goodsListVO.setImg(itemDO.getItemMainImage());
        goodsListVO.setIntro(itemDO.getItemTitle());
        goodsListVO.setCatgoryId(itemDO.getCategoryId());
        goodsListVO.setPrice(itemStockDOMapper.getMinPrice(itemId));
        goodsListVO.setItemSales(itemDOMapper.getItemSale(itemId));
        goodsListVO.setStock(itemStockDOMapper.getStockByItemId(itemId));
        return goodsListVO;
    }

    /**
     * 更局关键词返回商品列表
     * @param keyword
     * @return
     * @throws BusinessException
     */
    @Override
    public List<GoodsListVO> getItemByKeyWord(String keyword) throws BusinessException {
        List<GoodsListVO> goodsListVOS = new ArrayList<>();
        List<ItemDO> list = null;
        try{
            list = itemDOMapper.getItemByKeyword(keyword);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATABASE_ERROR);
        }
        for(ItemDO itemDO : list){
            goodsListVOS.add(convertItemDoToGoodsVo(itemDO));
        }
        return goodsListVOS;
    }

    /**
     * 根据itemId获得GoodsListVO
     * @param itemId
     * @return
     * @throws BusinessException
     */
    @Override
    public GoodsListVO getGoodsListVOByItemId(String itemId) throws BusinessException {
        try{
            return convertItemDoToGoodsVo(itemDOMapper.getItemByItemId(itemId));
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATABASE_ERROR);
        }
    }
}
