package com.donate.controller;

import com.donate.dto.reqdto.SpaceReqDTO;
import com.donate.entity.SpaceEntity;
import com.donate.service.ISpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("/space")
@RestController
public class SpaceController {

  @Autowired
  ISpaceService spaceService;

  @RequestMapping("/getSpaceList")
  Map<String,Object> getSpaceList(@RequestBody SpaceReqDTO reqDTO, HttpServletRequest request){
    Integer userId=0;
    if(request.getSession().getAttribute("user_id")!=null){
      userId= (Integer) request.getSession().getAttribute("user_id");
    }
    return spaceService.getSpaceList(reqDTO,userId);
  }

  @RequestMapping("/addSpace")
  Map<String,Object> addSpace(@RequestBody SpaceEntity spaceEntity,HttpServletRequest request){
    Integer userId=0;
    if(request.getSession().getAttribute("user_id")!=null){
      userId= (Integer) request.getSession().getAttribute("user_id");
    }
    spaceEntity.setAddUserId(userId);
    return spaceService.addSpace(spaceEntity);
  }

  @RequestMapping("/delSpace")
  Map<String,Object> delSpace(int id){
    return spaceService.delSpace(id);
  }

}
