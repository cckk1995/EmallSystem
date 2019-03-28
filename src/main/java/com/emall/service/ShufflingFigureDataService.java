package com.emall.service;

import com.emall.dataobject.ShufflingFigureDataDO;
import com.emall.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShufflingFigureDataService {
    void uploadShufflingFigureData(ShufflingFigureDataDO shufflingFigureDataDO) throws BusinessException;
    void deleteShufflingFigureData(String shufflingId) throws BusinessException;
    void modifyShufflingFigureData(ShufflingFigureDataDO shufflingFigureDataDO) throws BusinessException;
    List<ShufflingFigureDataDO> getAllShufflingFigure() throws BusinessException;
}
