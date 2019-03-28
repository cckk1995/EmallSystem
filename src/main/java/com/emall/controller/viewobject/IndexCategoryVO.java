package com.emall.controller.viewobject;

public class IndexCategoryVO {
    private String itemId;
    private String sourceUrl;
    private String buyUrl;
    private String imgUrl;
    private String name;
    private boolean buyStatus;

    public IndexCategoryVO(String itemId,String buyUrl,String imgUrl,String name,boolean buyStatus){
        this.itemId = itemId;
        this.sourceUrl = "/goodsDetail";
        this.buyUrl = buyUrl;
        this.imgUrl = imgUrl;
        this.name = name;
        this.buyStatus = buyStatus;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(boolean buyStatus) {
        this.buyStatus = buyStatus;
    }
}
