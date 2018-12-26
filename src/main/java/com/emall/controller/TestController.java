package com.emall.controller;

import com.emall.controller.viewobject.TestVO;
import com.emall.error.BusinessException;
import com.emall.error.EmBusinessError;
import com.emall.response.CommonReturnType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author XYY
 * @date 2018/12/26 10:52 AM
 */
@Controller("test")
@RequestMapping("/test")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class TestController extends BaseController {

    // 请求格式 /test/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType sayHello() {
        System.out.println("get request");
        return new CommonReturnType().create(null);
    }

    // 请求格式 /test/hello/1
    @RequestMapping(value = "/hello/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType hello(@PathVariable("id") int id) {
        TestVO testVO = new TestVO();
        testVO.setTestId(String.valueOf(id));
        testVO.setTestName("First Last");
        testVO.setTestContent("OPSDA");
        return new CommonReturnType().create(testVO);
    }

    // 请求格式 /test/hello1
    @RequestMapping(value = "/hello1", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType hello(@RequestParam(name = "id") String id,
                                  @RequestParam(name = "name") String name,
                                  @RequestParam(name = "content")String content) {
        TestVO testVO = new TestVO();
        testVO.setTestId(id);
        testVO.setTestName(name);
        testVO.setTestContent(content);
        return new CommonReturnType().create(testVO);
    }

    // 请求格式 /test/error
    @RequestMapping(value = "/error", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType error(@RequestParam(name = "id") String id,
                                  @RequestParam(name = "name") String name,
                                  @RequestParam(name = "content")String content) throws BusinessException {
        if (StringUtils.isEmpty(name)) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST, "用户名为空");
        }
        TestVO testVO = new TestVO();
        testVO.setTestId(id);
        testVO.setTestName(name);
        testVO.setTestContent(content);
        return CommonReturnType.create(testVO);
    }



}
