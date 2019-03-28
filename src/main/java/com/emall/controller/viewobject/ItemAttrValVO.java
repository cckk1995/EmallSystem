package com.emall.controller.viewobject;

/**
 * @author XYY
 * @date 2019-02-18 21:47
 */
public class ItemAttrValVO {
    private Integer attrKeyId;

    private String itemId;

    private Integer symbol;

    private String attrValue;

    private String attrImg;

    private Boolean select;

    public Integer getAttrKeyId() {
        return attrKeyId;
    }

    public void setAttrKeyId(Integer attrKeyId) {
        this.attrKeyId = attrKeyId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getSymbol() {
        return symbol;
    }

    public void setSymbol(Integer symbol) {
        this.symbol = symbol;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrImg() {
        return attrImg;
    }

    public void setAttrImg(String attrImg) {
        this.attrImg = attrImg;
    }

    public Boolean getSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }
}
