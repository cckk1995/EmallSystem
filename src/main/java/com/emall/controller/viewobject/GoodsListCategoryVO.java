package com.emall.controller.viewobject;

import com.emall.service.model.CategoryModel;

import java.util.List;

public class GoodsListCategoryVO {
    private CategoryModel father;
    private List<CategoryModel> child;

    public GoodsListCategoryVO(CategoryModel father, List<CategoryModel> child){
        this.father = father;
        this.child = child;
    }

    public CategoryModel getFather() {
        return father;
    }

    public void setFather(CategoryModel father) {
        this.father = father;
    }

    public List<CategoryModel> getChilds() {
        return child;
    }

    public void setChilds(List<CategoryModel> child) {
        this.child = child;
    }
}
