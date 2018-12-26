package com.emall.response;

/**
 * @author XYY
 * @date 2018/12/25 10:16 PM
 */
public class CommonReturnType {
    // 表明对应请求的返回处理结果"success"或"fail"
    private String status;
    // 若 status=success，则 data 内返回前端需要的 json 数据
    // 若 status=fail，则 data 内使用通用的错误码格式
    private Object data;

    // 定义一个通用的创建方法
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, "success");
    }
    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
