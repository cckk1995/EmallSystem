package com.emall.controller;

import com.emall.common.Const;
import com.emall.controller.viewobject.AddressVO;
import com.emall.controller.viewobject.UserVO;
import com.emall.error.BusinessException;
import com.emall.error.EmBusinessError;
import com.emall.response.CommonReturnType;
import com.emall.service.IUserService;
import com.emall.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * Created by kimvra on 2018/12/26
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public CommonReturnType login(String userName, String password,
                                  HttpSession session,
                                  HttpServletResponse response) throws BusinessException{
        CommonReturnType returnType = userService.login(userName, password);
        if (returnType.success()) {
            //写入cookie
            CookieUtil.writeLoginToken(response, session.getId());
            //写入redis
            redisTemplate.opsForValue().set(session.getId(), returnType.getData().toString(), Const.REDIS_SESSION_EXPIRETIME, TimeUnit.SECONDS);
        }
        return returnType;
    }

    @PostMapping("/register")
    @ResponseBody
    public CommonReturnType register(String username, String password,
                                     String confirmPassword, String email, String telephone) throws BusinessException {
        return userService.register(username, email, telephone, password, confirmPassword);
    }

    @GetMapping("/logout")
    public CommonReturnType logout(HttpServletRequest request, HttpServletResponse response) {
        String loginToken = CookieUtil.readLoginToken(request);
        CookieUtil.delLoginToken(request, response);
        redisTemplate.delete(loginToken);
        return CommonReturnType.create(null);
    }

    @PostMapping("userInfo")
    public CommonReturnType userInfo(@Valid UserVO userVO, BindingResult bindingResult,
                                     HttpServletRequest request) throws BusinessException {
        if (bindingResult.hasErrors()) {
            //System.out.println(bindingResult.getAllErrors().toString());
            log.error("参数错误", new BusinessException(EmBusinessError.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage()));
            throw new BusinessException(EmBusinessError.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }
//        String token = CookieUtil.readLoginToken(request);
//        if (StringUtils.isBlank(token)) {
//            log.error("token过期", new BusinessException(EmBusinessError.TOKEN_EXPIRED));
//            throw new BusinessException(EmBusinessError.TOKEN_EXPIRED);
//        }
//        String userId = redisTemplate.opsForValue().get(token);
        CommonReturnType returnType = userService.userInfo(userVO, request);
//        if (returnType.isSuccess()) {
//            redisTemplate.opsForValue().set(token, userId, Const.REDIS_SESSION_EXPIRETIME);
//        }
        return returnType;
    }

    @PostMapping("modifyEmail")
    public CommonReturnType modifyEmail(HttpServletRequest request, String email) throws BusinessException {
        if (StringUtils.isBlank(email)) {
            return CommonReturnType.create("邮箱不能为空");
        }
        return userService.modifyEmail(email, request);
    }

    @PostMapping("modifyTel")
    public CommonReturnType modifyTel(HttpServletRequest request, String tel) throws BusinessException {
        if (StringUtils.isBlank(tel)) {
            return CommonReturnType.create("电话不能为空");
        }
        return userService.modifyTel(tel, request);
    }

    @PostMapping("modifyPassword")
    public CommonReturnType modifyPassword(HttpServletRequest request, String oldPwd, String newPwd, String confirmPwd) throws BusinessException {
        if (StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd) || StringUtils.isBlank(confirmPwd)) {
            return CommonReturnType.create("密码不能为空");
        }
        return userService.modifyPassword(oldPwd, newPwd, confirmPwd, request);
    }

    @PostMapping("addAddress")
    public CommonReturnType addAddress(@Valid AddressVO addressVO,
                                       BindingResult bindingResult,
                                       HttpServletRequest request) throws BusinessException {
        if (bindingResult.hasErrors()) {
            //System.out.println(bindingResult.getAllErrors().toString());
            log.error("参数错误", new BusinessException(EmBusinessError.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage()));
            throw new BusinessException(EmBusinessError.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.addAddress(addressVO, request);
    }

    @GetMapping("delAddress")
    public CommonReturnType delAddress(String addressId, HttpServletRequest request) throws BusinessException {
        if (StringUtils.isBlank(addressId)) {
            log.error("参数错误", new BusinessException(EmBusinessError.PARAM_ERROR));
            throw new BusinessException(EmBusinessError.PARAM_ERROR);
        }
        return userService.delAddress(addressId, request);
    }

    @GetMapping("getAddresses")
    public CommonReturnType getAddresses(HttpServletRequest request) throws BusinessException {
        return userService.getAddresses(request);
    }

    @GetMapping("commentsBySeller")
    public CommonReturnType commentsBySeller(HttpServletRequest request) throws BusinessException {
        return userService.commentsBySeller(request);
    }

    @PostMapping("commentsByBuyer")
    public CommonReturnType commentsByBuyer(HttpServletRequest request, String orderItemId, String comment,
                                            @RequestParam(defaultValue = "1") String commentType, String commentImgUrl) throws BusinessException{
        return userService.commentsByUser(request, orderItemId, comment, commentType, commentImgUrl);
    }


    @GetMapping("/test")
    public String test() {
        log.info("test");
        return "test";
    }

}
