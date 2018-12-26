package com.emall.error;

/**
 * @author XYY
 * @date 2018/12/25 10:18 PM
 */
public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}
