package com.emall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emall.dataobject.ShufflingFigureDataDO;
import com.emall.error.BusinessException;
import com.emall.response.CommonReturnType;
import com.emall.service.ShufflingFigureDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shufflingFigureData")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ShufflingFigureDataController extends BaseController {
    @Autowired
    private ShufflingFigureDataService shufflingFigureDataService;

    /**
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/addShufflingFigureData",method = RequestMethod.POST)
        public CommonReturnType addShufflingFigureData(@RequestBody JSONObject jsonObject){
        ShufflingFigureDataDO shufflingFigureDataDO = JSON.toJavaObject(jsonObject,ShufflingFigureDataDO.class);
        //shufflingFigureDataDO.setCreatedDate(DateTimeUtil.getCurrentTime());
      //  shufflingFigureDataDO.setUpdatedDate(DateTimeUtil.getCurrentTime());
        try {
            shufflingFigureDataService.uploadShufflingFigureData(shufflingFigureDataDO);
        } catch (BusinessException e) {
            e.printStackTrace();
            return CommonReturnType.create(e.getErrMsg(),"false");
        }
        return CommonReturnType.create("轮播图添加成功");
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteShufflingFigureData",method = RequestMethod.GET)
    public CommonReturnType deleteShufflingFigureData(@RequestParam(value ="shufflingId")String id){
        try {
            shufflingFigureDataService.deleteShufflingFigureData(id);
        }catch (BusinessException e){
            e.printStackTrace();
            return CommonReturnType.create(e.getErrMsg(),"false");
        }
        return CommonReturnType.create("轮播图删除成功");
    }
    /**
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/modifyShufflingFigureData",method = RequestMethod.POST)
    public CommonReturnType modifyShufflingFigureData(@RequestBody JSONObject jsonObject){
        ShufflingFigureDataDO shufflingFigureDataDO = JSON.toJavaObject(jsonObject,ShufflingFigureDataDO.class);
     //   shufflingFigureDataDO.setUpdatedDate(DateTimeUtil.getCurrentTime());
        try {
            shufflingFigureDataService.modifyShufflingFigureData(shufflingFigureDataDO);
        } catch (BusinessException e) {
            e.printStackTrace();
            return CommonReturnType.create(e.getErrMsg(),"false");
        }
        return CommonReturnType.create("轮播图修改成功");
    }

    /**
     * 获得所有的轮播图
     * @return
     */
    @RequestMapping(value = "/getAllShuffleFigure",method = RequestMethod.GET)
    public CommonReturnType getAllShufflingFigure(){
        try{
            return CommonReturnType.create(shufflingFigureDataService.getAllShufflingFigure());
        }catch (BusinessException e){
            e.printStackTrace();
            return CommonReturnType.create(e.getErrMsg(),"false");
        }
    }
}
