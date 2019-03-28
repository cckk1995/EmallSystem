package com.emall.service.impl;

import com.emall.controller.viewobject.IndexCategoryListVO;
import com.emall.controller.viewobject.IndexCategoryVO;
import com.emall.dao.CategoryDOMapper;
import com.emall.dao.ItemDOMapper;
import com.emall.dataobject.CategoryDO;
import com.emall.dataobject.ItemDO;
import com.emall.error.BusinessException;
import com.emall.error.EmBusinessError;
import com.emall.service.CategoryService;
import com.emall.service.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDOMapper categoryDOMapper;

    @Autowired
    private ItemDOMapper itemDOMapper;


    /**
     * 通过父目录id获得子目录
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    public List<CategoryModel> getCategoryByParentId(int id) throws BusinessException {
       try{
           List<CategoryDO> list = categoryDOMapper.selectByParentId(id);
           List<CategoryModel> ans = new ArrayList<>();
           for(CategoryDO categoryDO : list){
               ans.add(CategoryDOTOCategoryModel(categoryDO));
           }
           return ans;
       }catch (Exception e){
           throw new BusinessException(EmBusinessError.DATABASE_ERROR);
       }
    }

    /**
     * 返回首页导航栏数据
     * @return
     * @throws BusinessException
     */
    @Override
    public List<IndexCategoryListVO> getIndexCategoryList() throws BusinessException {
        List<IndexCategoryListVO> indexCategoryListVOS = new ArrayList<>();
        try{
            List<CategoryDO> categoryDOS = categoryDOMapper.getAllCategory();
            int len = categoryDOS.size();
            for(int i = 0; i < len-1; i+=2){
                String name = categoryDOS.get(i).getCatName() + " " + categoryDOS.get(i+1).getCatName();
                List<IndexCategoryVO> indexCategoryVOS = ItemDOTOIndexCategoryVO(itemDOMapper.getItemByCatId(categoryDOS.get(i).getCatId()));
                indexCategoryVOS.addAll(ItemDOTOIndexCategoryVO(itemDOMapper.getItemByCatId(categoryDOS.get(i+1).getCatId())));
                indexCategoryListVOS.add(new IndexCategoryListVO(name,indexCategoryVOS));
            }
            return indexCategoryListVOS;
        }catch (Exception e) {
            throw new BusinessException(EmBusinessError.DATABASE_ERROR);
        }
    }

    private CategoryModel CategoryDOTOCategoryModel(CategoryDO categoryDO){
        return new CategoryModel(categoryDO.getCatId(),categoryDO.getCatParentId(),categoryDO.getCatName());
    }

    private List<IndexCategoryVO> ItemDOTOIndexCategoryVO(List<ItemDO> itemDOS){
        List<IndexCategoryVO> list = new ArrayList<>();
        for(ItemDO itemDO : itemDOS) {
            list.add(new IndexCategoryVO(itemDO.getItemId(),"/", itemDO.getItemMainImage(), itemDO.getItemTitle(), true));
        }
        return list;
    }
}
