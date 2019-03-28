package com.emall.controller.viewobject;

import java.math.BigDecimal;

/**
 * @author XYY
 * @date 2019/1/7 4:56 PM
 */
public class ShoppingcartVO {
    private String cartId;
    private String itemId;
    private String itemTitle;
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
