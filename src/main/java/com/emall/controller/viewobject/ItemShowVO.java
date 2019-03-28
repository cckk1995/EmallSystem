package com.emall.controller.viewobject;

import java.util.List;

public class ItemShowVO {
    private String itemId;
    private String itemTitle;
    private Integer itemSales;
    private String itemDetailImage;
    private String itemIntroImage;
    private List<MealVO> meal;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Integer getItemSales() {
        return itemSales;
    }

    public void setItemSales(Integer itemSales) {
        this.itemSales = itemSales;
    }

    public String getItemDetailImage() {
        return itemDetailImage;
    }

    public void setItemDetailImage(String itemDetailImage) {
        this.itemDetailImage = itemDetailImage;
    }

    public String getItemIntroImage() {
        return itemIntroImage;
    }

    public void setItemIntroImage(String itemIntroImage) {
        this.itemIntroImage = itemIntroImage;
    }

    public List<MealVO> getMeal() {
        return meal;
    }

    public void setMeal(List<MealVO> meal) {
        this.meal = meal;
    }
}
