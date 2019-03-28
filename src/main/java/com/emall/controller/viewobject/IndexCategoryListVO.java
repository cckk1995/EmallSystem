package com.emall.controller.viewobject;

import java.util.List;

public class IndexCategoryListVO {
    private String categoryName;
    private List<IndexCategoryVO> goods;

    public IndexCategoryListVO(String categoryName,List<IndexCategoryVO> goods){
        this.categoryName = categoryName;
        this.goods = goods;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<IndexCategoryVO> getList() {
        return goods;
    }

    public void setList(List<IndexCategoryVO> goods) {
        this.goods = goods;
    }
}
