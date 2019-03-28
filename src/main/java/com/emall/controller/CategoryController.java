package com.emall.controller;


import com.emall.controller.viewobject.GoodsListCategoryVO;
import com.emall.error.BusinessException;
import com.emall.response.CommonReturnType;
import com.emall.service.CategoryService;
import com.emall.service.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获得商品列表页的分类列表
     * @return
     */
    @RequestMapping(value = "/getCategory",method = RequestMethod.GET)
    public CommonReturnType getCategory(){
        List<GoodsListCategoryVO> goodsListCategoryVOS = new ArrayList<>();
        List<CategoryModel> categoryModelList = null;
        try{
            categoryModelList = categoryService.getCategoryByParentId(0);
            for(CategoryModel categoryModel : categoryModelList){
                int id = categoryModel.getId();
                List<CategoryModel> list = categoryService.getCategoryByParentId(id);
                goodsListCategoryVOS.add(new GoodsListCategoryVO(categoryModel,list));
            }
        }catch (BusinessException e){
            return CommonReturnType.create(e.getErrMsg(),"false");
        }
        return CommonReturnType.create(goodsListCategoryVOS);
    }

    /**
     * 获得首页的导航栏列表
     * @return
     */
    @RequestMapping(value = "/getIndexCategory",method = RequestMethod.GET)
    public CommonReturnType getIndexCategory(){
        try{
            return CommonReturnType.create(categoryService.getIndexCategoryList());
        }catch (BusinessException e){
            e.printStackTrace();
            return CommonReturnType.create(e.getErrMsg(),"false");
        }
    }
}
