package com.emall.service.model;

import java.math.BigDecimal;

/**
 * @author XYY
 * @date 2018/12/27 7:28 PM
 */
public class ShoppingcartModel {
    private String cartId;
    private String itemId;
    private String itemTitle;
    private String userId;
    private Integer stockId;
    private String attrImg;
    private BigDecimal price;
    private Integer amount;
    private String attrVals;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getAttrImg() {
        return attrImg;
    }

    public void setAttrImg(String attrImg) {
        this.attrImg = attrImg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAttrVals() {
        return attrVals;
    }

    public void setAttrVals(String attrVals) {
        this.attrVals = attrVals;
    }
}
