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

    CommonReturnType userInfo(UserVO userVO, HttpServletRequest request) throws BusinessException;

    CommonReturnType modifyEmail(String email, HttpServletRequest request) throws BusinessException;

    CommonReturnType modifyTel(String tel, HttpServletRequest request) throws BusinessException;

    CommonReturnType modifyPassword(String oldPwd, String newPwd, String confirmPwd, HttpServletRequest request) throws BusinessException;

    CommonReturnType addAddress(AddressVO addressVO, HttpServletRequest request) throws BusinessException;

    CommonReturnType delAddress(String addressId, HttpServletRequest request) throws BusinessException;

    CommonReturnType getAddresses(HttpServletRequest request) throws BusinessException;

    CommonReturnType commentsBySeller(HttpServletRequest request) throws BusinessException;

    CommonReturnType commentsByUser(HttpServletRequest request, String order_item_id, String comment,
                                    String commentType, String connect_img_url) throws BusinessException;
}
