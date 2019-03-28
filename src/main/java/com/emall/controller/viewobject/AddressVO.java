package com.emall.controller.viewobject;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Created by kimvra on 2019/1/1
 */
@Getter
@Setter
public class AddressVO {

    @NotBlank(message = "地址不能为空")
    private String address;

    @NotBlank(message = "地址详情不能为空")
    private String addressDetail;

    private String postalCode = "000000";

    @NotBlank(message = "收货人姓名不能为空")
    private String receiverName;

    @NotBlank(message = "手机号不能为空")
    private String receiverPhone;
}
