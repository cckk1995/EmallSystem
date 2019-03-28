package com.emall.controller.viewobject;

import java.util.Date;

public class CommentVO {
    private String username;
    private int values;
    private String content;
    private String goodsMeal;
    private Date createTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGoodsMeal() {
        return goodsMeal;
    }

    public void setGoodsMeal(String goodsMeal) {
        this.goodsMeal = goodsMeal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
