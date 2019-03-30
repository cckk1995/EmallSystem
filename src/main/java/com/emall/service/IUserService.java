package com.emall.service;

import com.emall.controller.viewobject.AddressVO;
import com.emall.controller.viewobject.UserVO;
import com.emall.error.BusinessException;
import com.emall.response.CommonReturnType;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kimvra on 2018/12/26
 */
public interface IUserService {

    CommonReturnType login(String username, String password) throws BusinessException;

    CommonReturnType register(String username, String email, String telephone,
                              String password, String confirmPassword) throws BusinessException;

    CommonReturnType userInfo(UserVO userVO, String userId) throws BusinessException;

    CommonReturnType modifyEmail(String email, String userId) throws BusinessException;

    CommonReturnType modifyTel(String tel, String userId) throws BusinessException;

    CommonReturnType modifyPassword(String oldPwd, String newPwd, String confirmPwd, String userId) throws BusinessException;

    CommonReturnType addAddress(AddressVO addressVO, String userId) throws BusinessException;

    CommonReturnType delAddress(String addressId, String userId) throws BusinessException;

    CommonReturnType getAddresses(String userId) throws BusinessException;

    CommonReturnType commentsBySeller(String userId) throws BusinessException;

    CommonReturnType commentsByUser(String userId, String order_item_id, String comment,
                                    String commentType, String connect_img_url) throws BusinessException;
}
