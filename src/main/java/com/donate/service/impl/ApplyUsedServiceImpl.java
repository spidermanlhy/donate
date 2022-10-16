package com.donate.service.impl;

import com.donate.entity.DtApplyEntity;
import com.donate.entity.DtApplyUsedEntity;
import com.donate.mapper.DtApplyMapper;
import com.donate.mapper.DtApplyUsedMapper;
import com.donate.service.ApplyUsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApplyUsedServiceImpl implements ApplyUsedService {

  @Autowired
  DtApplyUsedMapper dtApplyUsedMapper;

  @Override
  public Map<String, Object> addUsedRecoed(DtApplyUsedEntity applyUsedEntity) {
    Map<String,Object> result=new HashMap<>();
    applyUsedEntity.setCreateDate(new Date());
    dtApplyUsedMapper.insert(applyUsedEntity);
    result.put("code","ACK");
    result.put("message","添加成功");
    result.put("nonBizError",false);
    return result;
  }
}
