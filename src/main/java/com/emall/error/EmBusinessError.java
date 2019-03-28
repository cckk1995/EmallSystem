package com.emall.error;

/**
 * @author XYY
 * @date 2018/12/25 10:17 PM
 */
public enum EmBusinessError implements CommonError {
    // 通用错误类型 1000
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "未知错误"),
    DATABASE_ERROR(10003,"sql语句执行错误"),

    // 2000 开头为用户信息相关错误定义
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "用户手机号或密码不正确"),
    USER_NOT_LOGIN(20003, "用户还未登录"),
    USERNAME_EXISTS(20004, "用户名已存在"),
    USER_EMAIL_EXISTS(20005, "邮箱已绑定"),
    USER_TELEPHONE_EXISTS(20006, "电话已绑定"),
    USER_CHECK_TYPE_ERROR(20007, "校验参数类型错误"),
    USER_PASSWORD_CONFIRM_ERROR(20008, "密码不一致"),
    USER_REGISTER_FAIL(20009, "用户注册失败"),
    PARAM_ERROR(20010, "字段不能为空"),
    USER_CHECK_PARAM_ERROR(20011, "校验参数异常"),
    USER_PASSWORD_ERROR(20012, "用户密码错误"),
    UPDATE_USER_INFO_ERROR(20013, "更新个人信息失败"),
    TOKEN_EXPIRED(20014, "token过期,请重新登录"),

    // 3000 开头为交易信息错误定义
    STOCK_NOT_ENOUGH(30001, "库存不足"),

    // 4000 开头为购物车信息错误定义
    SHOPPINGCART_ITEM_NOT_EXIST(40001, "购物车商品不存在");

    private  int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
