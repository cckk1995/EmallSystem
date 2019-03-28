package com.emall.service.model;

public class CategoryModel {
    private int id;
    private int parentId;
    private String categoryName;

    public CategoryModel(int id,int parentId,String categoryName){
        this.id = id;
        this.parentId = parentId;
        this.categoryName = categoryName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
