package com.donate.controller;

import com.donate.dto.reqdto.SpaceReplyReqDTO;
import com.donate.dto.reqdto.SpaceReqDTO;
import com.donate.entity.SpaceEntity;
import com.donate.entity.SpaceReplyEntity;
import com.donate.service.ISpaceReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("/spaceReply")
@RestController
public class SpaceReplyController {
  @Autowired
  ISpaceReplyService spaceReplyService;

  @RequestMapping("/getSpaceList")
  Map<String,Object> getSpaceList(@RequestBody SpaceReplyReqDTO reqDTO, HttpServletRequest request){
    Integer userId=0;
    if(request.getSession().getAttribute("user_id")!=null){
      userId= (Integer) request.getSession().getAttribute("user_id");
    }
    return spaceReplyService.getSpaceReplyList(reqDTO,userId);
  }

  @RequestMapping("/addSpace")
  Map<String,Object> addSpace(@RequestBody SpaceReplyEntity spaceEntity,HttpServletRequest request){
    Integer userId=0;
    if(request.getSession().getAttribute("user_id")!=null){
      userId= (Integer) request.getSession().getAttribute("user_id");
    }
    spaceEntity.setAddUserId(userId);
    return spaceReplyService.addSpaceReply(spaceEntity);
  }

  @RequestMapping("/delSpace")
  Map<String,Object> delSpace(int id){
    return spaceReplyService.delSpaceReply(id);
  }
}
