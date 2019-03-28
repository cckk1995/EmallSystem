package com.emall.controller.viewobject;

public class GoodsListVO {
    private String itemId;
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    private String img;
    private String intro;
    private int itemSales;
    private int stock;
    private String catgoryId;

    public String getCatgoryId() {
        return catgoryId;
    }

    public void setCatgoryId(String catgoryId) {
        this.catgoryId = catgoryId;
    }

    public GoodsListVO(){
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public GoodsListVO(String itemId, String img, String intro, int stock){
        this.itemId = itemId;
        this.img = img;
        this.intro = intro;
        this.stock = stock;
    }

    public int getItemSales() {
        return itemSales;
    }

    public void setItemSales(int itemSales) {
        this.itemSales = itemSales;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
