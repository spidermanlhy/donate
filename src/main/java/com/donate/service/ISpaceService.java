package com.donate.service;

import com.donate.dto.reqdto.SpaceReqDTO;
import com.donate.entity.SpaceEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

public interface ISpaceService {
  Map<String,Object> getSpaceList(SpaceReqDTO reqDTO,Integer userId);

  Map<String,Object> addSpace(SpaceEntity spaceEntity);

  Map<String,Object> delSpace(int id);
}
