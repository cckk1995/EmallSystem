package com.emall.service;

import com.emall.controller.viewobject.IndexCategoryListVO;
import com.emall.error.BusinessException;
import com.emall.service.model.CategoryModel;

import java.util.List;

/**
 * created by cckk1995 on 2019/3/17
 */
public interface CategoryService {
    List<CategoryModel> getCategoryByParentId(int id) throws BusinessException;
    List<IndexCategoryListVO> getIndexCategoryList() throws BusinessException;
}
