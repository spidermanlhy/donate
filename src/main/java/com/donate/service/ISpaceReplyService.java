package com.donate.service;

import com.donate.dto.reqdto.SpaceReplyReqDTO;
import com.donate.dto.reqdto.SpaceReqDTO;
import com.donate.entity.SpaceEntity;
import com.donate.entity.SpaceReplyEntity;

import java.util.Map;

public interface ISpaceReplyService {
  Map<String,Object> getSpaceReplyList(SpaceReplyReqDTO reqDTO,Integer userId);

  Map<String,Object> addSpaceReply(SpaceReplyEntity spaceEntity);

  Map<String,Object> delSpaceReply(int id);
}
